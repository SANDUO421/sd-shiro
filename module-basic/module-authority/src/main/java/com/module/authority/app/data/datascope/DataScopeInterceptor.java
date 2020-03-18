package com.module.authority.app.data.datascope;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import com.module.authority.app.data.enmu.DataScopeTypeEnum;
import com.module.common.exception.PreBaseException;
import com.module.common.security.domain.PreSecurityUser;
import com.module.common.security.utils.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Mybatis 拦截器 主要用于数据权限拦截
 * Executor -> parameterHandler -> StatementHandler -> sql -> ResultSetHandler
 * <pre>
 *     mybatis拦截器
 *      在编写mybatis的拦截器之前，我们先来了解下mybaits的拦截目标方法
 *      1、Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
 *      2、ParameterHandler (getParameterObject, setParameters)
 *      3、StatementHandler (prepare, parameterize, batch, update, query)
 *      4、ResultSetHandler (handleResultSets, handleOutputParameters)
 *      这里选择StatementHandler 的 prepare 方法作为sql执行之前的拦截进行sql封装，使用ResultSetHandler 的 handleResultSets 方法作为sql执行之后的结果拦截过滤。
 * </pre>
 * @author 三多
 * @Time 2020/3/5
 */
@Order(90)
@Slf4j
@AllArgsConstructor
@Intercepts({@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class})})
@Component
public class DataScopeInterceptor extends AbstractSqlParserHandler implements Interceptor {
    private DataSource dataSource;

    /**
     * 拦截器
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("进入 DataScopeInterceptor 拦截器...");
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        //拦截 SQL 解析执行
        this.sqlParser(metaObject);
        //判断是不是select 操作不是直接过滤,通过反射获取delegate父类StatementHandler的mappedStatement属性
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if(! SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())){
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        //执行Sql语句
        String originalSql = boundSql.getSql();
        //SQl语句参数
        Object parameterObject = boundSql.getParameterObject();
        //查找参数中含有DataScope类型的是参数
        DataScope dataScope = findDataScopeObject(parameterObject);
        if(ObjectUtil.isNull(dataScope)){
            return invocation.proceed();
        }
        String scopeName = dataScope.getScopeName();
        List<Integer> deptIds = dataScope.getDeptIds();
        //优先获取赋值数据
        if(CollUtil.isEmpty(deptIds)){
            PreSecurityUser user = SecurityUtil.getUser();
            if(Objects.isNull(user)){
                throw new PreBaseException("auto dataScope, set up security details true");
            }
            //解析角色ID
            List<String> roleIdList = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(authority -> authority.startsWith("ROLE_"))
                    .map(authority -> authority.split("_")[1])
                    .collect(Collectors.toList());
            //通过角色Id查询范围权限
            Entity query = Db.use(dataSource)
                    .query("SELECT * FROM sys_role where role_id in (" + CollUtil.join(roleIdList,",") +")")
                    .stream().min(Comparator.comparingInt(o -> o.getInt("ds_type"))).get();
            // 数据库权限范围字段
            Integer dsType =query.getInt("ds_type");
            //查询全部
            if(DataScopeTypeEnum.ALL.getType() == dsType){
                return invocation.proceed();
            }
            //除了全部 则要获取自定义 本级及其下级 查询本级
            String dsScope = query.getStr("ds_scope");
            deptIds.addAll(Arrays.stream(dsScope.split(","))
                    .map(Integer::parseInt).collect(Collectors.toList()));
            String join = CollectionUtil.join(deptIds, ",");
            originalSql = "select * from (" + originalSql + ") temp_data_scope where temp_data_scope." + scopeName + " in (" + join + ")";
            metaObject.setValue("delegate.boundSql.sql",originalSql);
            return invocation.proceed();
        }
        return invocation.proceed();
    }

    /**
     * 查找参数中函数有DataScope对象
     * @param parameterObject 参数对象
     * @return DataScope
     */
    private DataScope findDataScopeObject(Object parameterObject) {
        if(parameterObject instanceof DataScope){
            return (DataScope)parameterObject;
        }else if(parameterObject instanceof Map){
            for(Object val : ((Map<?,?>)parameterObject).values()){
                if (val instanceof DataScope){
                    return (DataScope)val;
                }
            }
        }
        return null;
    }

    /**
     * 生成拦截对象的代理
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        if( target instanceof  StatementHandler){
            return Plugin.wrap(target,this);
        }
        return target;
    }

    /**
     * mybatis配置的属性
     * @param properties  mybatis配置的属性
     */
    @Override
    public void setProperties(Properties properties) {

    }
}

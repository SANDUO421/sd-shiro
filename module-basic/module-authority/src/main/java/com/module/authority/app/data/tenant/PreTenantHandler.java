package com.module.authority.app.data.tenant;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 租户处理器 -主要实现mybatis-plus https://mp.baomidou.com/guide/tenant.html
 * @author 三多
 * @Time 2020/3/5
 */
@Slf4j
@Component
public class PreTenantHandler implements TenantHandler {

    @Autowired
    private PreTenantConfigProperties tenantConfigProperties;
    /**
     * 获取租户 ID 值表达式，支持多个 ID 条件查询
     * <p>
     * 支持自定义表达式，比如：tenant_id in (1,2) @since 2019-8-2
     *
     * @param where 参数 true 表示为 where 条件 false 表示为 insert 或者 select 条件
     * @return 租户 ID 值表达式
     */
    @Override
    public Expression getTenantId(boolean where) {
        Long currentTenantId = PreTenantContextHolder.getCurrentTenantId();
        log.debug("当前租户是{}",currentTenantId);
        if(Objects.isNull(currentTenantId)){
            return new NullValue();
        }
        return new LongValue(currentTenantId);
    }

    /**
     * 获取租户字段名
     *
     * @return 租户字段名
     */
    @Override
    public String getTenantIdColumn() {
        return tenantConfigProperties.getTenantIdColumn();
    }

    /**
     * 根据表名判断是否进行过滤
     * 忽略掉一些表：如租户表（sys_tenant）本身不需要执行这样的处理
     * @param tableName 表名
     * @return 是否进行过滤, true:表示忽略，false:需要解析多租户字段
     */
    @Override
    public boolean doTableFilter(String tableName) {
        return tenantConfigProperties.getIgnoreTenantTables().stream().anyMatch(e -> e.equalsIgnoreCase(tableName) );
    }
}

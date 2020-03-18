package com.yulin.library.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.yulin.library.mybatis.model.entity.BaseEntity;
import com.yulin.library.util.Constants;
import com.yulin.library.util.MyBeanUtils;
import com.yulin.library.util.jwt.JwtUtils;
import com.yulin.library.util.model.page.OrderItem;
import com.yulin.library.util.model.page.PageResult;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class AbstractService<R extends BaseMapper<T>,T extends BaseEntity> extends ServiceImpl<R, T> implements BaseService<T> {

    @Autowired
    protected R baseMapper;

    @Autowired
    protected JwtUtils jwtUtils;

    @Override
    public List<T> list(QueryCondition queryCondition,Object... data) {
        updateQueryCondition(queryCondition);
        QueryWrapper<T> queryWrapper=new QueryWrapper<>();
        addListCondition(queryWrapper,data);
        List<OrderItem> orders = null;
        if (Objects.nonNull(queryCondition) && Objects.nonNull(orders = queryCondition.getOrders()) && !orders.isEmpty()) {
            createSort(queryCondition, queryWrapper);
        }
        return baseMapper.selectList(queryWrapper);
    }

    protected void updateQueryCondition(QueryCondition queryCondition) {
    }

    protected void addListCondition(QueryWrapper<T> queryWrapper, Object[] data){
    }

    @Override
    public PageResult listPage(QueryCondition queryCondition, Object... data) {
        updateQueryCondition(queryCondition);

        QueryWrapper<T> queryWrapper=new QueryWrapper<>();
        addListCondition(queryWrapper,data);

        List<OrderItem> orders = null;
        if (Objects.nonNull(queryCondition) && Objects.nonNull(orders = queryCondition.getOrders()) && !orders.isEmpty()) {
            createSort(queryCondition, queryWrapper);
        }
        PageResult result=new PageResult();

        if(queryCondition.isPageAble()){
            IPage pageResult = baseMapper.selectPage(new Page<>(queryCondition.getPageNumber() + 1, queryCondition.getPageSize()), queryWrapper);
            result.setTotal(pageResult.getTotal());
            result.setPageNumber(pageResult.getCurrent());
            result.setPageSize(pageResult.getSize());
            result.setData(pageResult.getRecords());
        }else{
            List list = baseMapper.selectList(queryWrapper);
            int size = list.size();
            result.setTotal(size);
            result.setPageNumber(1);
            result.setPageSize(size);
            result.setData(list);
        }

        return result;
    }

    protected void createSort(QueryCondition queryCondition, QueryWrapper<T> queryWrapper) {
        List<OrderItem> orders=queryCondition.getOrders();
        // 将驼峰命名转为下划线
        Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);

        orders.forEach(orderItem -> {
            if(orderItem.isAsc()){
                queryWrapper.orderByAsc(converter.convert(orderItem.getColumn()));
            }else{
                queryWrapper.orderByDesc(converter.convert(orderItem.getColumn()));
            }
        });
    }

    @Override
    public T findById(Long id) {
        beforeFindById(id);
        T entity=(T) baseMapper.selectById(id);
        afterFindById(entity);
        return entity;
    }

    protected void beforeFindById(Long id) {
    }

    protected void afterFindById(T entity) {
    }

    @Override
    public T add(T entity) throws Exception {
        entity.setCreateTime(new Date());
        entity.setCreateUserId(jwtUtils.getUserId());
        beforeAdd(entity);
        baseMapper.insert(entity);
        afterAdd(entity);
        return entity;
    }

    protected void beforeAdd(T entity) throws Exception {
    }

    protected void afterAdd(T entity) throws Exception {
    }

    @Override
    public T changeById(T entity) throws Exception {
        T oldEntity = this.findById(entity.getId());
        MyBeanUtils.copyProperties(entity,oldEntity);
        beforeUpdate(entity,oldEntity);
        oldEntity.setUpdateTime(new Date());
        oldEntity.setUpdateUserId(jwtUtils.getUserId());
        baseMapper.updateById(oldEntity);
        afterUpdate(oldEntity);
        return oldEntity;
    }

    protected void beforeUpdate(T entity,T oldEntity) throws Exception {
    }

    protected void afterUpdate(T entity) throws Exception {
    }

    @Override
    public T deleteById(Long id) throws Exception {
        T entity = (T)baseMapper.selectById(id);
        entity.setIsDelete(Constants.IS_DELETE);
        beforeDelete(entity);
        return this.changeById(entity);
    }

    protected void beforeDelete(T entity) {
    }

    @Override
    public Integer deleteByIds(List<Long> ids) throws Exception {
        Iterator<Long> iterator = ids.iterator();
        while(iterator.hasNext()){
            this.deleteById(iterator.next());
        }
        return ids.size();
    }

}

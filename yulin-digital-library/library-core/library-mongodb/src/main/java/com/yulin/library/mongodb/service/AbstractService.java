package com.yulin.library.mongodb.service;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import com.yulin.library.util.Constants;
import com.yulin.library.util.MyBeanUtils;
import com.yulin.library.util.jwt.JwtUtils;
import com.yulin.library.util.model.page.OrderItem;
import com.yulin.library.util.model.page.PageResult;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class AbstractService<R extends MongoRepository, T extends BaseEntity> implements BaseService<T> {

    @Autowired
    protected R repository;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected JwtUtils jwtUtils;

    @Override
    public List<T> list(QueryCondition queryCondition, Object... data) {
        updateQueryCondition(queryCondition);
        Criteria criteria = Criteria.where("isDelete").is(Constants.NOT_DELETE);
        addListCondition(criteria, data);
        Query query = Query.query(criteria);
        List<OrderItem> orders = null;
        if (Objects.nonNull(queryCondition) && Objects.nonNull(orders = queryCondition.getOrders()) && !orders.isEmpty()) {
            Sort sort = createSort(queryCondition);
            query.with(sort);
        }
        return mongoTemplate.find(query, getEntityClass());
    }

    protected void updateQueryCondition(QueryCondition queryCondition) {
    }

    protected void addListCondition(Criteria criteria, Object[] data) {
    }

    @Override
    public PageResult listPage(QueryCondition queryCondition, Object... data) {
        updateQueryCondition(queryCondition);
        Criteria criteria = Criteria.where("isDelete").is(Constants.NOT_DELETE);
        addListCondition(criteria, data);
        Query query = Query.query(criteria);

        long count = 0;

        List<OrderItem> orders = null;
        if (Objects.nonNull(queryCondition) && Objects.nonNull(orders = queryCondition.getOrders()) && !orders.isEmpty()) {
            Sort sort = createSort(queryCondition);
            if (Objects.nonNull(sort)) {
                query.with(sort);
            }
        }

        if (queryCondition.isPageAble()) {
            count = mongoTemplate.count(query, getEntityClass());
            query.with(PageRequest.of(queryCondition.getPageNumber(), queryCondition.getPageSize()));
        }

        List<T> contant = mongoTemplate.find(query, getEntityClass());

        PageResult result = new PageResult();
        result.setTotal(queryCondition.isPageAble() ? count : contant.size());
        result.setPageNumber(queryCondition.getPageNumber() + 1);
        result.setPageSize(queryCondition.getPageSize());
        result.setData(contant);

        return result;
    }

    protected Sort createSort(QueryCondition queryCondition) {
        List<OrderItem> orders = queryCondition.getOrders();
        OrderItem orderItem0 = orders.get(0);
        Sort result = null;
        if (orderItem0.isAsc()) {
            result = Sort.by(Sort.Direction.ASC, orderItem0.getColumn());
        } else {
            result = Sort.by(Sort.Direction.DESC, orderItem0.getColumn());
        }
        for (int i = 1, size = orders.size(); i < size; i++) {
            OrderItem orderItem = orders.get(i);
            if (orderItem.isAsc()) {
                result.and(Sort.by(Sort.Direction.ASC, orderItem.getColumn()));
            } else {
                result.and(Sort.by(Sort.Direction.DESC, orderItem.getColumn()));
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        beforeFindById(id);
        T entity = (T) repository.findById(id).get();
        afterFindById(entity);
        return entity;
    }

    protected void beforeFindById(String id) {
    }

    protected void afterFindById(T entity) {
    }

    @Override
    public T add(T entity) throws Exception {
        entity.setCreateTime(new Date());
        entity.setCreateUser(jwtUtils.getUserInfo());
        beforeAdd(entity);
        T result = (T) repository.save(entity);
        afterAdd(entity);
        return result;
    }

    protected void beforeAdd(T entity) throws Exception {
    }

    protected void afterAdd(T entity) throws Exception {
    }

    @Override
    public T updateById(T entity) throws Exception {
        T oldEntity = this.findById(entity.getId());
        MyBeanUtils.copyProperties(entity, oldEntity);
        beforeUpdate(entity, oldEntity);
        oldEntity.setUpdateTime(new Date());
        oldEntity.setUpdateUser(jwtUtils.getUserInfo());
        T result = (T) repository.save(oldEntity);
        afterUpdate(oldEntity);
        return result;
    }

    protected void beforeUpdate(T entity, T oldEntity) throws Exception {
    }

    protected void afterUpdate(T entity) throws Exception {
    }

    @Override
    public T deleteById(String id) throws Exception {
        T entity = (T) repository.findById(id).get();
        entity.setIsDelete(Constants.IS_DELETE);
        beforeDelete(entity);
        return this.updateById(entity);
    }

    protected void beforeDelete(T entity) {
    }

    @Override
    public Integer deleteByIds(List<String> ids) throws Exception {
        Iterator<String> iterator = ids.iterator();
        while (iterator.hasNext()) {
            String id = iterator.next();
            this.deleteById(id);
        }
        return ids.size();
    }

    protected abstract Class<T> getEntityClass();
}

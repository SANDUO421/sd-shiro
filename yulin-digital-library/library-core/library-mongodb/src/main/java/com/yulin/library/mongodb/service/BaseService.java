package com.yulin.library.mongodb.service;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import com.yulin.library.util.model.page.PageResult;
import com.yulin.library.util.model.page.QueryCondition;

import java.io.IOException;
import java.util.List;

public interface BaseService<T extends BaseEntity> {
    List<T> list(QueryCondition queryCondition,Object... data);

    PageResult listPage(QueryCondition queryCondition, Object... data);

    T findById(String id);

    T add(T dataDictionary) throws Exception;

    T updateById(T dataDictionary) throws IOException, Exception;

    T deleteById(String id) throws Exception;

    Integer deleteByIds(List<String> ids) throws Exception;
}

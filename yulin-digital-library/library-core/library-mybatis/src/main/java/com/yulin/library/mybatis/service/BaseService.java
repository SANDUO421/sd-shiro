package com.yulin.library.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yulin.library.mybatis.model.entity.BaseEntity;
import com.yulin.library.util.model.page.PageResult;
import com.yulin.library.util.model.page.QueryCondition;

import java.io.IOException;
import java.util.List;

public interface BaseService<T extends BaseEntity> extends IService<T> {
    List<T> list(QueryCondition queryCondition, Object... data);

    PageResult listPage(QueryCondition queryCondition, Object... data);

    T findById(Long id);

    T add(T dataDictionary) throws Exception;

    T changeById(T dataDictionary) throws IOException, Exception;

    T deleteById(Long id) throws Exception;

    Integer deleteByIds(List<Long> ids) throws Exception;
}

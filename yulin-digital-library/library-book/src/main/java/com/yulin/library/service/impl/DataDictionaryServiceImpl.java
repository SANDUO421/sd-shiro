package com.yulin.library.service.impl;

import com.yulin.library.model.entity.DataDictionary;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.DataDictionaryRepository;
import com.yulin.library.service.DataDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author lyyu1
 */
@Service
public class DataDictionaryServiceImpl extends AbstractService<DataDictionaryRepository,DataDictionary> implements DataDictionaryService {

    @Override
    protected void addListCondition(Criteria criteria, Object[] data) {
        if(data.length>0 && Objects.nonNull(data[0]) && StringUtils.isNoneBlank(data[0].toString())){
            criteria.and("type").is(data[0].toString());
        }
    }

    @Override
    protected Class<DataDictionary> getEntityClass() {
        return DataDictionary.class;
    }
}

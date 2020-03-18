package com.yulin.library.service.impl;

import com.yulin.library.model.entity.Version;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.VersionRepository;
import com.yulin.library.service.VersionService;
import com.yulin.library.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VersionServiceImpl extends AbstractService<VersionRepository, Version> implements VersionService {

    @Override
    protected void addListCondition(Criteria criteria, Object[] data) {
        if(Objects.nonNull(data[0])){
            criteria.and("type").is(data[0]);
        }
    }

    @Override
    protected void beforeAdd(Version entity) throws Exception {
        Version temp = repository.findFirstByIsDeleteAndTypeOrderByCodeDesc(Constants.NOT_DELETE, entity.getType());
        if(Objects.nonNull(temp)){
            entity.setCode(temp.getCode()+1);
        }else{
            entity.setCode(0L);
        }
    }

    @Override
    protected Class<Version> getEntityClass() {
        return Version.class;
    }

    @Override
    public Version getNewest(String type) {
        Query query=new Query();
        Criteria criteria = Criteria.where("isDelete").is(Constants.NOT_DELETE);
        if(StringUtils.isNoneBlank(type)){
            criteria.and("type").is(type);
        }
        Sort sort = Sort.by(Sort.Direction.DESC, "code");
        query.addCriteria(criteria);
        query.with(sort);
        return mongoTemplate.findOne(query, getEntityClass());
    }
}

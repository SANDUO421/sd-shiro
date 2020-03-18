package com.yulin.library.service.impl;

import com.yulin.library.model.entity.AppIcon;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.AppIconRepository;
import com.yulin.library.service.AppIconService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AppIconServiceImpl extends AbstractService<AppIconRepository, AppIcon> implements AppIconService {

    @Override
    protected void addListCondition(Criteria criteria, Object[] data) {
        if(Objects.nonNull(data[0])){
            criteria.and("position").is(data[0]);
        }
    }

    @Override
    protected Class<AppIcon> getEntityClass() {
        return AppIcon.class;
    }

}

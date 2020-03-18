package com.yulin.library.service.impl;

import com.yulin.library.model.entity.Banner;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.BannerRepository;
import com.yulin.library.service.BannerService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BannerServiceImpl extends AbstractService<BannerRepository, Banner> implements BannerService {

    @Override
    protected void addListCondition(Criteria criteria, Object[] data) {
        if(Objects.nonNull(data[0])){
            criteria.and("position").is(data[0]);
        }
    }

    @Override
    protected Class<Banner> getEntityClass() {
        return Banner.class;
    }

}

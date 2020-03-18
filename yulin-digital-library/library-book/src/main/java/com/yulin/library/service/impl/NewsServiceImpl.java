package com.yulin.library.service.impl;

import com.yulin.library.model.entity.News;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.NewsRepository;
import com.yulin.library.service.NewsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class NewsServiceImpl extends AbstractService<NewsRepository, News> implements NewsService {

    @Override
    protected void addListCondition(Criteria criteria, Object[] data) {
        if(Objects.nonNull(data[0]) && StringUtils.isNoneBlank(data[0].toString())){
            criteria.and("title").regex(data[0].toString());
        }
    }

    @Override
    protected Class<News> getEntityClass() {
        return News.class;
    }
}

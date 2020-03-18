package com.yulin.library.repository;

import com.yulin.library.model.entity.News;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsRepository extends MongoRepository<News,String> {

}

package com.yulin.library.repository;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommonRepository extends MongoRepository<BaseEntity,String> {
}

package com.yulin.library.repository;

import com.yulin.library.model.entity.DataDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataDictionaryRepository extends MongoRepository<DataDictionary,String> {
}

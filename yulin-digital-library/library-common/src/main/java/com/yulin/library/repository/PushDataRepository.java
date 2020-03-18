package com.yulin.library.repository;

import com.yulin.library.model.entity.FileData;
import com.yulin.library.model.entity.PushData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PushDataRepository extends MongoRepository<PushData,String> {
}

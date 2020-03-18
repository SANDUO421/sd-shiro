package com.yulin.library.repository;

import com.yulin.library.model.entity.FileData;
import com.yulin.library.mongodb.model.entity.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileDataRepository extends MongoRepository<FileData,String> {
}

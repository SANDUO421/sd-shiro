package com.yulin.library.repository;

import com.yulin.library.model.entity.Version;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VersionRepository extends MongoRepository<Version,String> {

    Version findFirstByIsDeleteAndTypeOrderByCodeDesc(Integer isDelete,String type);

}

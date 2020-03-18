package com.yulin.library.repository;

import com.yulin.library.model.entity.VipAuthority;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VipAuthorityRepository extends MongoRepository<VipAuthority,String> {

}

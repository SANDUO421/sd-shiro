package com.yulin.library.repository;

import com.yulin.library.model.entity.Banner;
import com.yulin.library.model.entity.VipAgreement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VipAgreementRepository extends MongoRepository<VipAgreement,String> {

}

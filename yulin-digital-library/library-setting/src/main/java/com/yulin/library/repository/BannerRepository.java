package com.yulin.library.repository;

import com.yulin.library.model.entity.Banner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BannerRepository extends MongoRepository<Banner,String> {

}

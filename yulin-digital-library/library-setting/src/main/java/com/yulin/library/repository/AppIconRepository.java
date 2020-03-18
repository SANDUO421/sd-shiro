package com.yulin.library.repository;

import com.yulin.library.model.entity.AppIcon;
import com.yulin.library.model.entity.Banner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppIconRepository extends MongoRepository<AppIcon,String> {

}

package com.yulin.library.repository;

import com.yulin.library.model.entity.UserComment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCommentRepository extends MongoRepository<UserComment,String> {
}

package com.yulin.library.repository;

import com.yulin.library.model.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question,String> {
}

package com.yulin.library.repository;

import com.yulin.library.model.entity.QuestionAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionAnswerRepository extends MongoRepository<QuestionAnswer,String> {
}

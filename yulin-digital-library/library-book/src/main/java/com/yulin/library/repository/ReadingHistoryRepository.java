package com.yulin.library.repository;

import com.yulin.library.model.entity.Book;
import com.yulin.library.model.entity.ReadingHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReadingHistoryRepository extends MongoRepository<ReadingHistory,String> {

}

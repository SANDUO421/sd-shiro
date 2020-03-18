package com.yulin.library.repository;

import com.yulin.library.model.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,String> {

}

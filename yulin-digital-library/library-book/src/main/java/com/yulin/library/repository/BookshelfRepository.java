package com.yulin.library.repository;

import com.yulin.library.model.entity.Bookshelf;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookshelfRepository extends MongoRepository<Bookshelf,String> {

    Bookshelf findFirstByIsDeleteAndCreateUserIdOrderBySortNumberDesc(Integer isDelete,Long userId);

}

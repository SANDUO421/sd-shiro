package com.yulin.library.service.impl;

import com.yulin.library.model.entity.Book;
import com.yulin.library.model.entity.BookContent;
import com.yulin.library.model.entity.ReadingHistory;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.BookContentRepository;
import com.yulin.library.repository.BookRepository;
import com.yulin.library.repository.ReadingHistoryRepository;
import com.yulin.library.service.BookService;
import com.yulin.library.service.ReadingHistoryService;
import com.yulin.library.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class ReadingHistoryServiceImpl extends AbstractService<ReadingHistoryRepository, ReadingHistory> implements ReadingHistoryService {

    @Override
    protected void beforeAdd(ReadingHistory entity) throws Exception {
        BookContent bookContent = mongoTemplate.findById(entity.getBookContentId(),BookContent.class);
        ReadingHistory readingHistory=findReadingHistory(bookContent.getBookId());
        if(Objects.nonNull(readingHistory)){
            entity.setBook(readingHistory.getBook());
            entity.setId(readingHistory.getId());
            return;
        }
        Book book = mongoTemplate.findById(bookContent.getBookId(),Book.class);
        entity.setBook(book);
    }

    private ReadingHistory findReadingHistory(String bookId) {
        Query query=new Query();
        Criteria criteria = Criteria.where("isDelete").is(Constants.NOT_DELETE);
        criteria.and("book.id").is(bookId).and("createUser.id").is(jwtUtils.getUserInfo().getId());
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query,ReadingHistory.class);
    }


    @Override
    protected Class<ReadingHistory> getEntityClass() {
        return ReadingHistory.class;
    }
}

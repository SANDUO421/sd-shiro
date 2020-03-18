package com.yulin.library.service.impl;

import com.mongodb.client.result.UpdateResult;
import com.yulin.library.model.entity.Book;
import com.yulin.library.model.entity.Bookshelf;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.BookRepository;
import com.yulin.library.repository.BookshelfRepository;
import com.yulin.library.service.BookshelfService;
import com.yulin.library.util.Constants;
import com.yulin.library.util.model.page.OrderItem;
import com.yulin.library.util.model.page.QueryCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookshelfServiceImpl extends AbstractService<BookshelfRepository, Bookshelf> implements BookshelfService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    protected void updateQueryCondition(QueryCondition queryCondition) {
        List<OrderItem> orders = queryCondition.getOrders();
        if (Objects.isNull(orders) || orders.isEmpty()){
            orders=new ArrayList<>();
        }
        orders.add(OrderItem.asc("sortNumber"));
        queryCondition.setOrders(orders);
    }

    @Override
    protected void addListCondition(Criteria criteria, Object[] data) {
        if(Objects.nonNull(data[0])){
            criteria.and("createUser.id").is(data[0]);
        }
        if(Objects.nonNull(data[1]) && StringUtils.isNoneBlank(data[1].toString())){
            criteria.orOperator(Criteria.where("book.name").regex(data[1].toString()),Criteria.where("books.name").regex(data[1].toString()));
        }
        if(data.length>=3 && Objects.nonNull(data[2]) && StringUtils.isNoneBlank(data[2].toString())){
            criteria.and("book.id").is(data[2].toString());
        }
    }

    @Override
    protected void beforeAdd(Bookshelf entity) throws Exception {
        Bookshelf bookshelf=findIsAdd(entity.getBook().getId());
        if(Objects.nonNull(bookshelf)){
            throw new Exception("该图书已添加，请勿重复添加！");
        }
        String id = entity.getBook().getId();
        Book book = bookRepository.findById(id).get();
        Bookshelf temp = repository.findFirstByIsDeleteAndCreateUserIdOrderBySortNumberDesc(Constants.NOT_DELETE, jwtUtils.getUserId());
        if(Objects.nonNull(temp)){
            entity.setSortNumber(temp.getSortNumber()+1);
        }else{
            entity.setSortNumber(0L);
        }
        entity.setBook(book);
    }

    private Bookshelf findIsAdd(String bookId) {
        Query query=new Query();
        Criteria criteria = Criteria.where("isDelete").is(Constants.NOT_DELETE);
        criteria.orOperator(Criteria.where("book.id").is(bookId),Criteria.where("books.id").is(bookId));
        criteria.and("createUser.id").is(jwtUtils.getUserInfo().getId());
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, getEntityClass());
    }

    @Override
    protected Class<Bookshelf> getEntityClass() {
        return Bookshelf.class;
    }

    @Override
    public UpdateResult updateSortNumber(Long userId, Integer scope) {
        Query query=new Query();
        Criteria criteria = Criteria.where("createUser.id").is(userId);
        query.addCriteria(criteria);

        Update update=new Update();
        update.inc("sortNumber",scope);

        return mongoTemplate.updateMulti(query, update, Bookshelf.class);
    }
}

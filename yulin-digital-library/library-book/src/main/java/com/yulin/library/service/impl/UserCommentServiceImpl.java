package com.yulin.library.service.impl;

import com.yulin.library.model.entity.Book;
import com.yulin.library.model.entity.UserComment;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.UserCommentRepository;
import com.yulin.library.service.BookService;
import com.yulin.library.service.UserCommentService;
import com.yulin.library.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class UserCommentServiceImpl extends AbstractService<UserCommentRepository, UserComment> implements UserCommentService {

    @Autowired
    private BookService bookService;

    @Override
    protected void beforeUpdate(UserComment entity,UserComment oldEntity) throws Exception {
        Date now=new Date();
        // 如果是审核通过
        if(Constants.AuditState.INSANITY.equals(entity.getState())){
            entity.setPassTime(now);
        }
        // 如果是审核不通过
        if(Constants.AuditState.AUDIT_FAILS.equals(entity.getState())){
            entity.setRejectTime(now);
        }
    }

    @Override
    protected void afterUpdate(UserComment entity) throws Exception {
        // 修改问题是否需要审核
        changeBookIsverify(entity);
    }

    private void changeBookIsverify(UserComment entity) throws Exception {
        Query query=new Query();
        Criteria criteria = Criteria.where("isDelete").is(Constants.NOT_DELETE)
                .and("state").is(Constants.AuditState.WAITCONFIRM)
                .and("book").is(entity.getBook());
        query.addCriteria(criteria);
        long count = mongoTemplate.count(query, getEntityClass());
        if(count==0){
            changeBookIsverify(entity,0);
        }
    }

    private void changeBookIsverify(UserComment entity,Integer state) throws Exception {
        Book book = entity.getBook();
        book.setIsverify(state);
        bookService.updateById(book);
    }

    @Override
    protected void afterAdd(UserComment entity) throws Exception {
        if(Constants.AuditState.WAITCONFIRM.equals(entity.getState())){
            // 新增以后的book内容不一定会有
            entity=repository.findById(entity.getId()).get();
            changeBookIsverify(entity,1);
        }
    }

    @Override
    protected void addListCondition(Criteria criteria, Object[] data) {
        if(Objects.nonNull(data[0])){
            criteria.and("content").regex(data[0].toString());
        }
        if(Objects.nonNull(data[1])){
            criteria.and("state").is(data[1]);
        }
        if(Objects.nonNull(data[2]) && !"".equals(data[2].toString())){
            Book book=new Book();
            book.setId(data[2].toString());
            criteria.and("book").is(book);
        }
    }

    @Override
    protected Class<UserComment> getEntityClass() {
        return UserComment.class;
    }

}

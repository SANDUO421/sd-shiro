package com.yulin.library.service.impl;

import cn.hutool.core.io.FileUtil;
import com.mongodb.client.result.DeleteResult;
import com.yulin.library.model.entity.Book;
import com.yulin.library.model.entity.BookContent;
import com.yulin.library.model.entity.DataDictionary;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.BookContentRepository;
import com.yulin.library.repository.BookRepository;
import com.yulin.library.service.BookService;
import com.yulin.library.util.BookUtils;
import com.yulin.library.util.model.entity.BaseBookContent;
import com.yulin.library.util.model.entity.CurrentUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class BookServiceImpl extends AbstractService<BookRepository, Book> implements BookService {

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @Autowired
    private BookContentRepository bookContentRepository;

    @Override
    protected void addListCondition(Criteria criteria, Object[] data) {
        if(Objects.nonNull(data[0]) && StringUtils.isNoneBlank(data[0].toString())){
            criteria.and("name").regex(data[0].toString());
        }
        if(Objects.nonNull(data[1]) && StringUtils.isNoneBlank(data[1].toString())){
            List<String> datas=(List<String>)data[1];
            List<DataDictionary> types=new ArrayList<>(datas.size());
            datas.forEach(id->{
                DataDictionary dataDictionary=new DataDictionary();
                dataDictionary.setId(id);
                types.add(dataDictionary);
            });
            criteria.and("category").all(types);
        }
        if(Objects.nonNull(data[2])){
            criteria.and("isFree").is(data[2]);
        }
        if(Objects.nonNull(data[3])){
            criteria.and("isRecommend").is(data[3]);
        }
        if(Objects.nonNull(data[4])){
            criteria.and("type").is(data[4]);
        }
        if(Objects.nonNull(data[5])){
            criteria.and("isVip").is(data[5]);
        }
        if(Objects.nonNull(data[6])){
            criteria.and("isFamous").is(data[6]);
        }
    }

    /**
     * 修改查看次数，方便计算热门图书
     * @param entity
     */
    @Override
    protected void afterFindById(Book entity) {
        Query query=new Query();
        Criteria criteria = Criteria.where("id").is(entity.getId());
        query.addCriteria(criteria);

        Update update=new Update();
        update.inc("viewCount",1);

        mongoTemplate.updateMulti(query, update, getEntityClass());
    }

    @Override
    protected void afterAdd(Book entity) throws Exception {
        String contentUrl = entity.getContentUrl();
        if(StringUtils.isNoneBlank(contentUrl)){
            String extName = FileUtil.extName(contentUrl);
            entity.setContentType(extName);
            if(StringUtils.isNoneBlank(extName) && extName.toLowerCase().equals("txt")){
                addContent(entity);
            }
        }else{
            // 如果是图书，并且没有附件id，则默认为png（图书）
            if(Objects.nonNull(entity.getType()) && entity.getType().intValue()==BOOK.intValue()){
                entity.setContentType("png");
            }
        }
        repository.save(entity);
    }

    @Override
    protected void afterUpdate(Book entity) throws Exception {
        String contentUrl = entity.getContentUrl();
        if(StringUtils.isNoneBlank(contentUrl)){
            String extName = FileUtil.extName(contentUrl);
            entity.setContentType(extName);
            deleteContent(entity);
            if(StringUtils.isNoneBlank(extName) && extName.toLowerCase().equals("txt")){
                addContent(entity);
            }
        }
    }

    private long deleteContent(Book entity) {
        Criteria criteria = Criteria.where("bookId").is(entity.getId());
        DeleteResult deleteResult = mongoTemplate.remove(Query.query(criteria), BookContent.class);
        return deleteResult.getDeletedCount();
    }

    private void addContent(Book entity) throws Exception {
        File file=new File(fileUploadPath+entity.getContentUrl());
        List<BaseBookContent> list= BookUtils.shardByChapter(file);
        Iterator<BaseBookContent> iterator = list.iterator();
        BookContent before=null;
        Date now = new Date();
        CurrentUser userInfo = jwtUtils.getUserInfo();
        while(iterator.hasNext()){
            BaseBookContent baseBookContent = iterator.next();
            BookContent bookContent=new BookContent();
            bookContent.setBookContent(baseBookContent);
            bookContent.setBookId(entity.getId());
            bookContent.setCreateTime(now);
            bookContent.setCreateUser(userInfo);
            if(Objects.nonNull(before)){
                bookContent.setBeforeId(before.getId());
            }
            bookContentRepository.save(bookContent);
            if(Objects.nonNull(before)){
                before.setNextId(bookContent.getId());
                bookContentRepository.save(before);
            }
            before=bookContent;
        }
    }

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}

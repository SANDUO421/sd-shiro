package com.yulin.library.service.impl;

import com.mongodb.client.result.UpdateResult;
import com.yulin.library.feign.PayService;
import com.yulin.library.model.entity.Book;
import com.yulin.library.model.entity.BookContent;
import com.yulin.library.model.entity.Bookshelf;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.BookContentRepository;
import com.yulin.library.service.BookContentService;
import com.yulin.library.service.BookService;
import com.yulin.library.service.BookshelfService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.Constants;
import com.yulin.library.util.MyBeanUtils;
import com.yulin.library.util.jwt.JwtUtils;
import com.yulin.library.util.model.page.OrderItem;
import com.yulin.library.util.model.page.QueryCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class BookContentServiceImpl extends AbstractService<BookContentRepository, BookContent> implements BookContentService {

    @Autowired
    private BookshelfService bookshelfService;

    @Autowired
    private PayService payService;

    @Autowired
    private BookService bookService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void afterFindById(BookContent entity) {
        // 判断是否有权限（是否VIP，是否购买等）
        judgeHavePermission(entity);
        // 修改书架顺序号
        changeBookshelfSortNumber(entity);
    }

    private void judgeHavePermission(BookContent entity) {
        Book book = bookService.findById(entity.getBookId());
        if(BookService.VIP_BOOK.equals(book.getIsVip())){
            isVip(book);
        }
        if(BookService.NOT_FREE.equals(book.getIsFree())){
            isFree(book);
        }
    }

    private void isFree(Book book) {
        ApiResponse apiResponse = payService.get(request.getHeader("Authorization"), book.getId(),String.valueOf(jwtUtils.getUserId()),1);
        Map<Object,Object> data = ((Map) apiResponse.getData());
        if(Objects.isNull(data) || data.isEmpty()){
            throw new RuntimeException("请购买此付费书");
        }
    }

    private void isVip(Book book) {
        ApiResponse apiResponse = payService.get(request.getHeader("Authorization"), String.valueOf(jwtUtils.getUserId()));
        Integer isVip = (Integer) ((Map) apiResponse.getData()).get("isVip");
        if(Objects.isNull(isVip) || 1!=isVip.intValue()){
            throw new RuntimeException("不是会员无法查看本书");
        }
    }

    private void changeBookshelfSortNumber(BookContent entity) {
        String bookId = entity.getBookId();
        Long userId=jwtUtils.getUserInfo().getId();
        List<Bookshelf> list = bookshelfService.list(new QueryCondition(), userId, null, bookId);
        if(Objects.nonNull(list) && !list.isEmpty()){
            Bookshelf bookshelf = list.get(0);
            if (Objects.nonNull(bookshelf.getSortNumber()) && 0L!=bookshelf.getSortNumber().longValue()){
                try {
                    changeBookshelfSortNumber(bookshelf);
                } catch (Exception e) {
                    new RuntimeException(e);
                }
            }
        }
    }

    private void changeBookshelfSortNumber(Bookshelf bookshelf) throws Exception {
        bookshelfService.updateSortNumber(bookshelf.getCreateUser().getId(),1);
        bookshelf.setSortNumber(0L);
        bookshelfService.updateById(bookshelf);
    }

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
            criteria.and("bookId").regex(data[0].toString());
        }
    }

    @Override
    public BookContent add(BookContent entity) throws Exception {
        BookContent result=null;
        entity.setCreateTime(new Date());
        entity.setCreateUser(jwtUtils.getUserInfo());
        BookContent last=repository.findFirstByBookIdOrderBySortNumberDesc(entity.getBookId());
        if(Objects.isNull(entity.getSortNumber())){
            result = addBookContentNoSortNumber(entity, last);
        }else{
            result = addBookContentWithSortNumber(entity, last);
        }
        return result;
    }

    private BookContent addBookContentWithSortNumber(BookContent entity, BookContent last) throws Exception {
        BookContent result = null;
        BookContent bookContent = repository.findFirstByBookIdAndSortNumberAndIsDelete(entity.getBookId(), entity.getSortNumber(), Constants.NOT_DELETE);
        if(Objects.nonNull(bookContent)){
            throw new Exception("顺序号重复！");
        }
        if(Objects.nonNull(last)){
            if(entity.getSortNumber().compareTo(last.getSortNumber())>0){
                result = repository.save(entity);
                last.setNextId(result.getId());
                repository.save(last);
            }else if(entity.getSortNumber().compareTo(last.getSortNumber())<0){
                List<BookContent> list = changeSortNumber(entity,true);
                changeSortNumber(entity.getBookId(),entity.getSortNumber(),last.getSortNumber(),1);
                repository.saveAll(list);
                result=entity;
            }else{
                throw new Exception("顺序号重复！");
            }
        }else{
            result = repository.save(entity);
        }
        return result;
    }

    private BookContent addBookContentNoSortNumber(BookContent entity, BookContent last) {
        BookContent result;
        if(Objects.isNull(last)){
            entity.setSortNumber(1L);
        }else{
            entity.setSortNumber(last.getSortNumber()+1L);
            entity.setBeforeId(last.getId());
        }
        result = repository.save(entity);
        if(Objects.nonNull(last)){
            last.setNextId(result.getId());
            repository.save(last);
        }
        return result;
    }

    @Override
    public BookContent updateById(BookContent entity) throws Exception {
        BookContent oldEntity = this.findById(entity.getId());
        entity.setBookId(oldEntity.getBookId());
        // 如果顺序号不相同，则交换顺序
        if(!oldEntity.getSortNumber().equals(entity.getSortNumber())){
            changeSortNumber(entity,oldEntity);
        }
        MyBeanUtils.copyProperties(entity,oldEntity);
        oldEntity.setUpdateTime(new Date());
        oldEntity.setUpdateUser(jwtUtils.getUserInfo());
        BookContent result = repository.save(oldEntity);
        return result;
    }

    @Override
    protected void beforeDelete(BookContent entity) {
        String beforeId = entity.getBeforeId();
        String nextId = entity.getNextId();
        if(StringUtils.isNoneBlank(beforeId)){
            BookContent bookContent = repository.findById(beforeId).get();
            bookContent.setNextId(nextId);
            repository.save(bookContent);
        }
        if(StringUtils.isNoneBlank(nextId)){
            BookContent bookContent = repository.findById(nextId).get();
            bookContent.setBookId(beforeId);
            repository.save(bookContent);
        }
    }

    private void changeSortNumber(BookContent entity, BookContent oldEntity) {
        List<BookContent> changeList=new ArrayList<>(5);

        BookContent bookContent=repository.findFirstByBookIdAndSortNumberAndIsDelete(entity.getBookId(),entity.getSortNumber(),Constants.NOT_DELETE);
        if(Objects.isNull(bookContent)){
            List<BookContent> entityList = changeSortNumber(entity,true);
            if(Objects.nonNull(entityList.get(0))){
                oldEntity.setBeforeId(entityList.get(0).getId());
                changeList.add(entityList.get(0));
            }
            if(Objects.nonNull(entityList.get(1))){
                oldEntity.setNextId(entityList.get(1).getId());
                changeList.add(entityList.get(1));
            }
        }else{
            changeList.add(bookContent);
        }

        List<BookContent> oldEntityList = changeSortNumber(oldEntity,false);
        if(Objects.nonNull(oldEntityList.get(0))){
            changeList.add(oldEntityList.get(0));
        }
        if(Objects.nonNull(oldEntityList.get(1))){
            changeList.add(oldEntityList.get(1));
        }

        if(entity.getSortNumber().compareTo(oldEntity.getSortNumber())>0){
            if(Objects.nonNull(bookContent)){
                oldEntity.setBeforeId(bookContent.getId());
                oldEntity.setNextId(bookContent.getNextId());
                if(Objects.nonNull(bookContent.getNextId())){
                    BookContent next = repository.findById(bookContent.getNextId()).get();
                    if(Objects.nonNull(next)){
                        next.setBeforeId(oldEntity.getId());
                        changeList.add(next);
                    }
                }
                bookContent.setNextId(entity.getId());
            }
            repository.saveAll(changeList);
            changeSortNumber(entity.getBookId(),oldEntity.getSortNumber(),entity.getSortNumber(),0);
        }else{
            if(Objects.nonNull(bookContent)){
                oldEntity.setBeforeId(bookContent.getBeforeId());
                oldEntity.setNextId(bookContent.getId());
                if(Objects.nonNull(bookContent.getBeforeId())){
                    BookContent before = repository.findById(bookContent.getBeforeId()).get();
                    if(Objects.nonNull(before)){
                        before.setNextId(oldEntity.getId());
                        changeList.add(before);
                    }
                }
                bookContent.setBeforeId(entity.getId());
            }
            repository.saveAll(changeList);
            changeSortNumber(entity.getBookId(),entity.getSortNumber(),oldEntity.getSortNumber(),1);
        }
    }

    private List<BookContent> changeSortNumber(BookContent entity,boolean flag){
        Date now=new Date();

        BookContent entityLessThan = repository.findFirstByBookIdAndSortNumberIsLessThanOrderBySortNumberDesc(entity.getBookId(), entity.getSortNumber());
        BookContent entityGreaterThan = repository.findFirstByBookIdAndSortNumberIsGreaterThanOrderBySortNumberAsc(entity.getBookId(), entity.getSortNumber());

        if(Objects.nonNull(entityLessThan)){
            if(flag){
                entityLessThan.setNextId(entity.getId());
            }else{
                if(Objects.nonNull(entityGreaterThan)){
                    entityLessThan.setNextId(entityGreaterThan.getId());
                }else{
                    entityLessThan.setNextId(null);
                }
            }
            entityLessThan.setUpdateTime(now);
            entityLessThan.setUpdateUser(jwtUtils.getUserInfo());
        }

        if(Objects.nonNull(entityGreaterThan)){
            if (flag){
                entityGreaterThan.setBeforeId(entity.getId());
            }else{
                if(Objects.nonNull(entityLessThan)){
                    entityGreaterThan.setBeforeId(entityLessThan.getId());
                }else{
                    entityGreaterThan.setBeforeId(null);
                }
            }
            entityGreaterThan.setUpdateTime(now);
            entityGreaterThan.setUpdateUser(jwtUtils.getUserInfo());
        }

        return Arrays.asList(entityLessThan,entityGreaterThan);
    }

    /**
     *
     * @param bookId            书id
     * @param minSortNumber     最小顺序号
     * @param maxSortNumber     最大顺序号
     * @param type              类型（0-减，1-加）
     * @return
     */
    private UpdateResult changeSortNumber(String bookId,Long minSortNumber,Long maxSortNumber,int type){
        Query query=new Query();
        Update update=new Update();
        Criteria criteria = Criteria.where("bookId").is(bookId);

        if(0==type){
            criteria.and("sortNumber").lte(maxSortNumber).gt(minSortNumber);
            update.inc("sortNumber",-1);
        }else{
            criteria.and("sortNumber").lt(maxSortNumber).gte(minSortNumber);
            update.inc("sortNumber",1);
        }

        query.addCriteria(criteria);

        return mongoTemplate.updateMulti(query, update, BookContent.class);
    }

    @Override
    protected Class<BookContent> getEntityClass() {
        return BookContent.class;
    }
}

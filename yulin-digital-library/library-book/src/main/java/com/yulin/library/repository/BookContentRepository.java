package com.yulin.library.repository;

import com.yulin.library.model.entity.Book;
import com.yulin.library.model.entity.BookContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BookContentRepository extends MongoRepository<BookContent,String> {

    BookContent findFirstByBookIdOrderBySortNumberDesc(String bookId);

    BookContent findFirstByBookIdAndSortNumberIsLessThanEqualOrderBySortNumberDesc(String bookId,Long sortNumber);

    /**
     * 查询比本章节顺序号要小的最大的章节
     * @param bookId
     * @param sortNumber
     * @return
     */
    BookContent findFirstByBookIdAndSortNumberIsLessThanOrderBySortNumberDesc(String bookId,Long sortNumber);

    /**
     * 查询比本章节顺序号要大的最小的章节
     * @param bookId
     * @param sortNumber
     * @return
     */
    BookContent findFirstByBookIdAndSortNumberIsGreaterThanOrderBySortNumberAsc(String bookId,Long sortNumber);

    BookContent findFirstByBookIdAndSortNumberAndIsDelete(String bookId,Long sortNumber,Integer isDelete);

}

package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 书架
 */
@Data
@Document
public class Bookshelf extends BaseEntity {
    private static final long serialVersionUID = 7971732859592054323L;

    private String name;
    /**
     * 分类（0-图书，1-书架）
     */
    private Integer type;

    private Book book;

    private Long sortNumber;

    private List<Book> books;

}

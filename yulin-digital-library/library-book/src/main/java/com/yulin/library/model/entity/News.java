package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class News extends BaseEntity {
    private static final long serialVersionUID = -2887007498362555804L;

    private String title;
    private String author;
    private String content;
    /**
     * 封面图片
     */
    private String photoUrl;

}

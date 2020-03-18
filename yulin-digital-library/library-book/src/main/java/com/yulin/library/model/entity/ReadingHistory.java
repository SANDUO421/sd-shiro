package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ReadingHistory extends BaseEntity {
    private static final long serialVersionUID = -1921189812702972289L;

    private Book book;

    private String bookContentId;

}

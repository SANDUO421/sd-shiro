package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员权益
 * @author lyyu1
 */
@Data
@Document
public class VipAuthority extends BaseEntity {
    private static final long serialVersionUID = 6852179419688007531L;

    /**
     * 权益名称
     */
    private String name;
    /**
     * 图标
     */
    private String photoUrl;
    /**
     * 内容
     */
    private String content;
}

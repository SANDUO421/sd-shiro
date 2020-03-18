package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 会员协议
 * @author lyyu1
 */
@Data
@Document
public class VipAgreement extends BaseEntity {
    private static final long serialVersionUID = 6852179419688007531L;

    /**
     * 协议名称
     */
    private String name;
    /**
     * 内容
     */
    private String content;
}

package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

/**
 * @author lyyu1
 */
@Data
public class UserComment extends BaseEntity {
    private static final long serialVersionUID = 1708012365940171697L;
    /**
     * 评论内容
     */
    private String content;

    @DBRef
    private Book book;
    /**
     * 状态（0-待审核，1-审核通过，100-审核不通过）
     */
    private Integer state;
    /**
     * 审核意见
     */
    private String auditOpinion;
    /**
     * 审核通过时间
     */
    private Date passTime;
    /**
     * 审核拒绝时间
     */
    private Date rejectTime;

}

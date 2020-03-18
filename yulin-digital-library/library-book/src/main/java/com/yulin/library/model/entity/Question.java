package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Question extends BaseEntity {
    private static final long serialVersionUID = 4533889498356294517L;

    /**
     * 问题内容
     */
    private String content;
    /**
     * 回答条数
     */
    private Long answerCount=0L;
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
    /**
     * 审核状态（0-没有需要审核的回答，1-有需要审核的回答）
     */
    private Integer isverify=0;

}

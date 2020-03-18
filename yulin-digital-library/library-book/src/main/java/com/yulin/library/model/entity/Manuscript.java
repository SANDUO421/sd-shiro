package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 提交的稿件
 */
@Data
public class Manuscript extends BaseEntity {
    private static final long serialVersionUID = 5698743031592547466L;

    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 附件地址
     */
    private String attachmentPath;
    /**
     * 提交人状态（0-保存，1-提交）
     */
    private Integer userState;
    /**
     * 审核状态（0-待审核，1-审核通过，100-审核不通过）
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

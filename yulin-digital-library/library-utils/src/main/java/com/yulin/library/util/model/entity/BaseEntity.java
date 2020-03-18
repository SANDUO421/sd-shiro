package com.yulin.library.util.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = -2876798311608823707L;

    private Long id;
    private Date createTime;
    private CurrentUser createUser;
    private Date updateTime;
    private CurrentUser updateUser;
    /**
     * 是否删除（0-未删除，1-删除）
     */
    private Integer isDelete=0;

}

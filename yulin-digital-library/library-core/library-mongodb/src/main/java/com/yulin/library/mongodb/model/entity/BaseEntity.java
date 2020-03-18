package com.yulin.library.mongodb.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.entity.CurrentUser;
import lombok.Data;

import java.util.Date;

@JsonView(ApiResponse.BaseJsonView.class)
@Data
public class BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = -2876798311608823707L;

    private String id;
    private Date createTime;
    private CurrentUser createUser;
    private Date updateTime;
    private CurrentUser updateUser;
    /**
     * 是否删除（0-未删除，1-删除）
     */
    private Integer isDelete=0;

}

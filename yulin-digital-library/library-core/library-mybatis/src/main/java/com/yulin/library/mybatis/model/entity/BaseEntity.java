package com.yulin.library.mybatis.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = -2876798311608823707L;

    private Long id;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT)
    private Long createUserId;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUserId;
    private Date deleteTime;
    private Long deleteUserId;
    /**
     * 是否删除（0-未删除，1-删除）
     */
    @TableLogic
    private Integer isDelete=0;

}

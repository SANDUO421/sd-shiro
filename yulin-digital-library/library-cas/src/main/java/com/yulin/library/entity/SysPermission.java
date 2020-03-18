package com.yulin.library.entity;

import com.yulin.library.mybatis.entity.BaseEntity;
import lombok.Data;

@Data
public class SysPermission extends BaseEntity {
    private static final long serialVersionUID = -5585706883839071286L;

    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 权限表达式
     */
    private String permissionExpression;
}

package com.yulin.library.entity;

import com.yulin.library.mybatis.model.entity.BaseEntity;
import lombok.Data;

@Data
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 8198009678430146354L;

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色表达式
     */
    private String roleExpression;
    /**
     * 角色描述
     */
    private String roleDescription;

}

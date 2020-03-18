package com.yulin.library.entity;

import com.yulin.library.mybatis.entity.BaseEntity;
import lombok.Data;

@Data
public class SysMenu extends BaseEntity {
    private static final long serialVersionUID = 8198009678430146354L;

    /**
     * 父id
     */
    private Long parentId;
    /**
     * 菜单等级
     */
    private Integer level;
    /**
     * 排序号
     */
    private Integer sortNumber;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单类型
     */
    private SysMenuType menuType;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单连接
     */
    private String url;
    /**
     * 菜单描述
     */
    private String menuDescription;

}

package com.module.authority.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:    菜单Dto
 * @author:         sanduo
 * @date:           2020/3/4 12:23
 * @version:        1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuDTO {

    private static final long serialVersionUID = 1L;

    private Integer menuId;
    private String name;
    private String perms;
    private String path;
    private Boolean isFrame;
    private Integer parentId;
    private String component;
    private String icon;
    private Integer sort;
    private Integer type;
    private String delFlag;

}

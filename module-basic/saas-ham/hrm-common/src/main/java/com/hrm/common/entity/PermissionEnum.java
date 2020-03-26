package com.hrm.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.Time;

/**
 * 权限类型
 * @author 三多
 * @Time 2020/3/24
 */
@Getter
@AllArgsConstructor
public enum PermissionEnum {
    MENU_AND_POINT(0,"菜单和按钮"),
    MENU(1,"菜单"),
    POINT(2,"功能按钮"),
    API(3,"API");

    /**
     * 代号
     */
    int code;
    /**
     * 描述
     */
    String desc;
}

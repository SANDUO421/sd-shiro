package com.module.authority.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:    菜单元数据
 * @author:         sanduo
 * @date:           2020/3/4 12:40
 * @version:        1.0
 */
@Data
@AllArgsConstructor
public class MenuMetaVo implements Serializable {

    private String title;
    private String icon;
}

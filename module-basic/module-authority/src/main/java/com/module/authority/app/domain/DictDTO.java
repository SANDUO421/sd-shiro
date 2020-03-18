package com.module.authority.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:    字典dto
 * @author:         sanduo
 * @date:           2020/3/4 12:23
 * @version:        1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictDTO {


    private Integer id;

    private String dictName;

    private String dictCode;

    private String description;

    private Integer sort;

    private String remark;
}

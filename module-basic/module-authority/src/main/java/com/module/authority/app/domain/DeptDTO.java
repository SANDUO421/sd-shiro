package com.module.authority.app.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:    UserDTO
 * @author:         sanduo
 * @date:           2020/3/4 12:22
 * @version:        1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptDTO {

    private static final long serialVersionUID = 1L;

    private Integer deptId;

    /**
     * 部门名称
     */
    private String name;


    /**
     * 上级部门
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;


}

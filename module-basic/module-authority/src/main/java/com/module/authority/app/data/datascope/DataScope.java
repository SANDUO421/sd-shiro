package com.module.authority.app.data.datascope;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 数据权限查询参数
 * @author 三多
 * @Time 2020/3/4
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataScope  extends HashMap {
    /**限制范围的字段名称*/
    private String scopeName = "deptId";
    /**具体的数据范围*/
    private List<Integer> deptIds = new ArrayList<>();
    /**是否只查询本部门*/
    private Boolean isOnly = false;
}

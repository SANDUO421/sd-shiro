package com.hrm.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author 三多
 * @Time 2020/3/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    /**总条数*/
    private Long total;
    /**数据*/
    private List<T> rows;
}

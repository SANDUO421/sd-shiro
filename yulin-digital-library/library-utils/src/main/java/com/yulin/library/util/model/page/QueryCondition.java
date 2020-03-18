package com.yulin.library.util.model.page;

import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Author 白尚兵
 * @Email 556bsb@163.com
 * @Date 2019/8/5 16:29
 **/
@Data
public class QueryCondition {
    /**
     * 分页-起始页
     */
    private int pageNumber=0;
    /**
     * 分页-每页数量
     */
    private int pageSize=10;
    /**
     * 是否分页
     */
    private boolean pageAble=true;
    /**
     * 排序
     */
    private List<OrderItem> orders;
}

package com.yulin.library.util.model.page;

import com.fasterxml.jackson.annotation.JsonView;
import com.yulin.library.util.ApiResponse;
import lombok.Data;

import java.util.List;
import java.util.Map;

@JsonView(ApiResponse.BaseJsonView.class)
@Data
public class PageResult<T> implements java.io.Serializable {
    private static final long serialVersionUID = 5098359014045394868L;

    /**
     * 总记录数
     */
    private long total;
    /**
     * 每页数
     */
    private long pageSize;
    /**
     * 当前页数
     */
    private long pageNumber=0L;
    /**
     * 总页数
     */
    private long pageTotal;
    /**
     * 结果集
     */
    private List<T> data;
    /**
     * 其他数据
     */
    private Map<String,Object> otherData;

    public long getPageTotal() {
        if(pageSize==0L){
            return 0;
        }
        return total/pageSize+(total%pageSize==0?0:1);
    }
}

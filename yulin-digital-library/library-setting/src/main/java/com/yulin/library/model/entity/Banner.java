package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;

/**
 * 轮播图
 * @author lyyu1
 */
@Data
public class Banner extends BaseEntity {
    private static final long serialVersionUID = -8044344215877667195L;
    private String position;
    private String positionName;
    private String imageUrl;
    private String linkUrl;
    private Integer sortNum;
    /**
     * banner类型（homePage-首页，newsPage-新闻页面）
     */
    private String type;
}

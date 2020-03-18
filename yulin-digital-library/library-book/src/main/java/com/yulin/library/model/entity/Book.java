package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document
public class Book extends BaseEntity {

    /**
     * 书名
     */
    private String name;
    /**
     * 作者
     */
    private String author;
    /**
     * 出版社
     */
    private String publishingHouse;
    /**
     * 类别
     */
    @DBRef
    private List<DataDictionary> category;
    /**
     * 主题
     */
    @DBRef
    private List<DataDictionary> theme;
    /**
     * 是否精品（0-不是，1-是）
     */
    private Integer isBoutique=0;
    /**
     * 是否VIP（0-不是，1-是）
     */
    private Integer isVip=0;
    /**
     * 是否免费（0-不免费，1-免费）
     */
    private Integer isFree=0;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 是否主编力推（0-不是，1-是）
     */
    private Integer isRecommend=0;
    /**
     * 是否名家著作（0-不是，1-是）
     */
    private Integer isFamous=0;
    /**
     * 描述
     */
    private String description;
    /**
     * 积分
     */
    private BigDecimal integral;
    /**
     * 封面图片
     */
    private String photoUrl;
    /**
     * 内容附件地址
     */
    private String contentUrl;
    /**
     * 附件类型（txt,pdf,jpg,png等）
     */
    private String contentType;
    /**
     * 分类（1-图书，2-音频，3-视频，4-文章）
     */
    private Integer type;
    /**
     * 查看次数
     */
    private Long viewCount;
    /**
     * 审核状态（0-没有需要审核的评论，1-有需要审核的评论）
     */
    private Integer isverify=0;
}

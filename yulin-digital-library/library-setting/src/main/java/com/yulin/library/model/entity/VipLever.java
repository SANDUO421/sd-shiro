package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lyyu1
 */
@Data
public class VipLever extends BaseEntity {
    private static final long serialVersionUID = -6448023134160194674L;
    /**
     * 会员卡名称
     */
    private String name;
    /**
     * 售价
     */
    private BigDecimal price;
    /**
     * 原价
     */
    private BigDecimal originalPrice;
    /**
     * 时长
     */
    private Integer day;
    /**
     * 描述
     */
    private String description;

}

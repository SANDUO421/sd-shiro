package com.yulin.library.entity;

import com.yulin.library.mybatis.model.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BaseUserOrder extends BaseEntity {

    /**
     * 用户id
     */
    private String userId;
    /**
     * 支付状态：0、未支付；1、已支付；
     */
    private Integer payState;
    /**
     * 支付方式：1、支付宝；2、微信；3、余额；99、其他
     */
    private Integer payMode;
    /**
     * 支付id（支付宝或者微信支付id）
     */
    private String payId;
    /**
     * 订单充值时间
     */
    private Date orderTime;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 支付订单id（UserOrderDetail的id)
     */
    private Long userOrderDetailId;

}

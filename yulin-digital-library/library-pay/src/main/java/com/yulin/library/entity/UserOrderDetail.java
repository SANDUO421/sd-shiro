package com.yulin.library.entity;

import com.baomidou.mybatisplus.annotation.Version;
import com.yulin.library.mybatis.model.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户所有订单
 * @author 星中月
 * @since 2018/7/6 0006
 */
@Data
public class UserOrderDetail extends BaseEntity {
    private static final long serialVersionUID = -7541858460912375656L;

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
     * 支付方（微信、支付宝）返回的支付id
     */
    private String payId;
    /**
     * 订单充值时间
     */
    private Date orderTime;
    /**
     * 支付金额
     */
    private BigDecimal price;
    /**
     * 支付是否成功，0未成功，1成功
     */
    private Integer isSuccess;
    /**
     * 充值失败返回说明
     */
    private String payMessage;
    /**
     * 订单类型（userBookOrder为购买图书；userVipOrder为购买vip支付；userRechargeOrder为充值支付）
     */
    private String orderType;
    /**
     * 订单id（userBookOrder实体的id；userVipOrder实体的id；userRechargeOrder实体的id）
     */
    private Long orderId;
    /**
     * 版本号
     */
    @Version
    private Long version;
    /**
     * 终端IP（微信支付需要）
     */
    private String spBillCreateIp;

}

package com.yulin.library.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yulin.library.entity.BaseUserOrder;
import com.yulin.library.entity.UserOrderDetail;
import com.yulin.library.mybatis.service.BaseService;

import java.math.BigDecimal;

public interface BaseUserOrderService<T extends BaseUserOrder> extends BaseService<T> {

    /**
     * 创建支付订单
     * @param payMode           支付方式：1、支付宝；2、微信；3、余额；4、其他
     * @param price             支付金额
     * @param orderType         订单类型（userBookOrder为购买图书；userVipOrder为购买vip支付；userRechargeOrder为充值支付）
     * @param orderId           订单id（userBookOrder实体的id；userVipOrder实体的id；userRechargeOrder实体的id）
     * @param userId            用户 id
     * @return
     */
    UserOrderDetail createUserOrderDetail(Integer payMode, BigDecimal price, String orderType, Long orderId, String userId);

    /**
     * 创建订单
     * @param order
     * @return
     */
    T createOrder(T order);

    /**
     * 订单支付成功后操作
     * @param userOrderDetail
     * @param entity
     */
    void afterPay(UserOrderDetail userOrderDetail, BaseUserOrder entity);

}

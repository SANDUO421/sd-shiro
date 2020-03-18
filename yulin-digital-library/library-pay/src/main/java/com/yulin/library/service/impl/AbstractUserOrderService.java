package com.yulin.library.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulin.library.entity.BaseUserOrder;
import com.yulin.library.entity.UserOrderDetail;
import com.yulin.library.mapper.UserOrderDetailMapper;
import com.yulin.library.mybatis.service.AbstractService;
import com.yulin.library.service.BaseUserOrderService;
import com.yulin.library.util.jwt.JwtUtils;
import com.yulin.library.util.pay.PayConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public abstract class AbstractUserOrderService<M extends BaseMapper<T>,T extends BaseUserOrder> extends AbstractService<M, T> implements BaseUserOrderService<T> {

    @Autowired
    protected UserOrderDetailMapper userOrderDetailMapper;

    @Autowired
    protected JwtUtils jwtUtils;

    /**
     * 创建支付订单
     * @param payMode           支付方式：1、支付宝；2、微信；3、余额；4、其他
     * @param price             支付金额
     * @param orderType         订单类型（userBookOrder为购买图书；userVipOrder为购买vip支付；userRechargeOrder为充值支付）
     * @param orderId           订单id（userBookOrder实体的id；userVipOrder实体的id；userRechargeOrder实体的id）
     * @param userId            用户 id
     * @return
     */
    @Transactional
    @Override
    public UserOrderDetail createUserOrderDetail(Integer payMode, BigDecimal price, String orderType, Long orderId, String userId){
        UserOrderDetail userOrderDetail = userOrderDetailMapper.findByOrderTypeAndOrderId(orderType, orderId);
        if (Objects.isNull(userOrderDetail)) {
            userOrderDetail = new UserOrderDetail();
        }
        userOrderDetail.setOrderType(orderType);
        userOrderDetail.setOrderId(orderId);
        userOrderDetail.setUserId(userId);
        userOrderDetail.setPrice(price);
        userOrderDetail.setPayMode(payMode);
        userOrderDetail.setPayState(PayConstants.PayState.NOT_PAY);
        userOrderDetail.setCreateUserId(jwtUtils.getUserId());
        userOrderDetail.setCreateTime(new Date());
        userOrderDetailMapper.insert(userOrderDetail);
        return userOrderDetail;
    }

    @Transactional
    @Override
    public T createOrder(T order) {
        long orderId = IdWorker.getId();
        Long userId=jwtUtils.getUserId();
        order.setId(orderId);
        order.setUserId(String.valueOf(userId));
        BigDecimal price = getPrice(order);
        order.setPrice(price);
        order.setPayState(PayConstants.PayState.NOT_PAY);
        order.setCreateUserId(jwtUtils.getUserId());
        order.setCreateTime(new Date());

        UserOrderDetail userOrderDetail = createUserOrderDetail(order.getPayMode(), price, getOrderType(order), orderId, String.valueOf(userId));
        order.setUserOrderDetailId(userOrderDetail.getId());
        baseMapper.insert(order);

        return order;
    }

    protected abstract String getOrderType(T order);

    protected abstract BigDecimal getPrice(T order);

}

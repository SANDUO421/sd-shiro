package com.yulin.library.service.impl;

import com.yulin.library.entity.BaseUserOrder;
import com.yulin.library.entity.SysUserInfo;
import com.yulin.library.entity.UserOrderDetail;
import com.yulin.library.entity.UserRechargeOrder;
import com.yulin.library.mapper.SysUserInfoMapper;
import com.yulin.library.mapper.UserOrderDetailMapper;
import com.yulin.library.mapper.UserRechargeOrderMapper;
import com.yulin.library.mybatis.service.AbstractService;
import com.yulin.library.service.BaseUserOrderService;
import com.yulin.library.service.SysUserInfoService;
import com.yulin.library.service.UserOrderDetailService;
import com.yulin.library.util.Constants;
import com.yulin.library.util.jwt.JwtUtils;
import com.yulin.library.util.pay.PayConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
public class UserOrderDetailServiceImpl extends AbstractService<UserOrderDetailMapper, UserOrderDetail> implements UserOrderDetailService {

    @Autowired
    private UserOrderDetailMapper userOrderDetailMapper;

    @Autowired
    private UserRechargeOrderMapper userRechargeOrderMapper;

    @Autowired
    private SysUserInfoService sysUserInfoService;

    @Autowired
    private Map<String, BaseUserOrderService> baseUserOrderServiceMap;

    @Autowired
    protected JwtUtils jwtUtils;

    /**
     * 支付平台回调service（加锁，防止高并发下用户金额出问题）
     *
     * @param id         订单id
     * @param payId      支付宝或者微信支付id
     * @param isSuccess  支付是否成功，0未成功，1成功
     * @param payMessage 支付失败返回说明
     * @param payMode    支付方式：1、支付宝；2、微信；3、余额；4、其他
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public synchronized UserOrderDetail pay(Long id, String payId, Integer isSuccess, String payMessage, Integer payMode) throws Exception {
        UserOrderDetail userOrderDetail = userOrderDetailMapper.selectById(id);
        Integer payState = userOrderDetail.getPayState();
        if(Objects.nonNull(payState) && PayConstants.PayState.HAVE_PAY.equals(payState)){
            throw new Exception("订单已支付");
        }
        String serviceName=userOrderDetail.getOrderType()+"ServiceImpl";
        BaseUserOrderService baseUserOrderService=baseUserOrderServiceMap.get(serviceName);
        BaseUserOrder t = (BaseUserOrder) baseUserOrderService.getById(userOrderDetail.getOrderId());
        payState = t.getPayState();
        if(Objects.nonNull(payState) && PayConstants.PayState.HAVE_PAY.equals(payState)){
            throw new Exception("订单已支付");
        }

        userOrderDetail.setPayId(payId);
        userOrderDetail.setIsSuccess(isSuccess);
        userOrderDetail.setPayMessage(payMessage);
        userOrderDetail.setPayMode(payMode);
        userOrderDetail.setPayState(PayConstants.PayState.HAVE_PAY);

        t.setPayId(payId);
        t.setPayMode(payMode);
        t.setPayState(PayConstants.PayState.HAVE_PAY);

        if(PayConstants.PayModel.YU_E.equals(payMode)) {
            // 如果用余额充值余额，则抛出异常
            if(PayConstants.OrderType.USER_RECHARGE_ORDER.equals(userOrderDetail.getOrderType())){
                throw new Exception("不能用余额充值余额");
            }
            payWithBalance(userOrderDetail);
        }

        return this.payOrder(userOrderDetail,t,baseUserOrderService);
    }

    private synchronized UserOrderDetail payOrder(UserOrderDetail userOrderDetail, BaseUserOrder entity, BaseUserOrderService baseUserOrderService) {
        userOrderDetailMapper.updateById(userOrderDetail);
        baseUserOrderService.updateById(entity);
        baseUserOrderService.afterPay(userOrderDetail,entity);
        return userOrderDetail;
    }

    private void payWithBalance(UserOrderDetail userOrderDetail) throws Exception {
        // 如果为余额支付，则判断余额是否充足
        SysUserInfo sysUserInfo = sysUserInfoService.selectByUserId(userOrderDetail.getUserId());
        BigDecimal remainingBalance;
        if(Objects.isNull(sysUserInfo) || Objects.isNull(remainingBalance=sysUserInfo.getRemainingBalance())){
            throw new Exception("支付失败");
        }
        BigDecimal price = userOrderDetail.getPrice();
        // 如果价格大于余额
        if(price.compareTo(remainingBalance)>0){
            throw new Exception("余额不足");
        }

        // 记录余额消费
        recordUserRechargeOrder(userOrderDetail);

        sysUserInfo.setRemainingBalance(remainingBalance.subtract(price));
        sysUserInfoService.updateById(sysUserInfo);
    }

    private void recordUserRechargeOrder(UserOrderDetail userOrderDetail){
        Date now=new Date();
        UserRechargeOrder userRechargeOrder=new UserRechargeOrder();
        userRechargeOrder.setOrderTime(now);
        userRechargeOrder.setPayId(userOrderDetail.getPayId());
        userRechargeOrder.setPayState(PayConstants.PayState.HAVE_PAY);
        userRechargeOrder.setPayMode(userOrderDetail.getPayMode());
        userRechargeOrder.setPrice(userOrderDetail.getPrice().negate());
        userRechargeOrder.setUserId(userOrderDetail.getUserId());
        userRechargeOrder.setUserOrderDetailId(userOrderDetail.getId());
        userRechargeOrder.setIsDelete(Constants.NOT_DELETE);
        userRechargeOrder.setCreateTime(new Date());
        userRechargeOrder.setCreateUserId(jwtUtils.getUserId());
        userRechargeOrderMapper.insert(userRechargeOrder);
    }
}

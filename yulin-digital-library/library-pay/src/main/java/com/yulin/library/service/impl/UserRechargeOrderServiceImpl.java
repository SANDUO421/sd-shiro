package com.yulin.library.service.impl;

import com.yulin.library.entity.BaseUserOrder;
import com.yulin.library.entity.SysUserInfo;
import com.yulin.library.entity.UserOrderDetail;
import com.yulin.library.entity.UserRechargeOrder;
import com.yulin.library.mapper.UserRechargeOrderMapper;
import com.yulin.library.service.SysUserInfoService;
import com.yulin.library.service.UserRechargeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class UserRechargeOrderServiceImpl extends AbstractUserOrderService<UserRechargeOrderMapper, UserRechargeOrder> implements UserRechargeOrderService {

    @Autowired
    private SysUserInfoService sysUserInfoService;

    @Override
    protected String getOrderType(UserRechargeOrder order) {
        return "userRechargeOrder";
    }

    @Override
    protected BigDecimal getPrice(UserRechargeOrder order) {
        return order.getPrice();
    }

    @Override
    public void afterPay(UserOrderDetail userOrderDetail, BaseUserOrder baseUserOrder) {
        UserRechargeOrder entity=(UserRechargeOrder) baseUserOrder;
        SysUserInfo sysUserInfo = sysUserInfoService.selectByUserId(entity.getUserId());
        BigDecimal remainingBalance = sysUserInfo.getRemainingBalance();
        if(Objects.isNull(remainingBalance)){
            sysUserInfo.setRemainingBalance(userOrderDetail.getPrice());
        }else{
            sysUserInfo.setRemainingBalance(remainingBalance.add(userOrderDetail.getPrice()));
        }
        sysUserInfoService.updateById(sysUserInfo);
    }

}

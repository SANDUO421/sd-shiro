package com.yulin.library.service.impl;

import cn.hutool.core.date.DateUtil;
import com.yulin.library.entity.BaseUserOrder;
import com.yulin.library.entity.SysUserInfo;
import com.yulin.library.entity.UserOrderDetail;
import com.yulin.library.entity.UserVipOrder;
import com.yulin.library.feign.VipLeverService;
import com.yulin.library.mapper.UserVipOrderMapper;
import com.yulin.library.service.SysUserInfoService;
import com.yulin.library.service.UserVipOrderService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.pay.PayConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
public class UserVipOrderServiceImpl extends AbstractUserOrderService<UserVipOrderMapper, UserVipOrder> implements UserVipOrderService {

    @Autowired
    private VipLeverService vipLeverService;

    @Autowired
    private SysUserInfoService sysUserInfoService;

    @Autowired
    private HttpServletRequest request;

    @Override
    protected String getOrderType(UserVipOrder order) {
        return "userVipOrder";
    }

    @Override
    protected BigDecimal getPrice(UserVipOrder order) {
        ApiResponse apiResponse = vipLeverService.get(request.getHeader("Authorization"),order.getVipLevelId());
        Map data = (Map) apiResponse.getData();
        return new BigDecimal(data.get("price").toString());
    }

    @Override
    public void afterPay(UserOrderDetail userOrderDetail, BaseUserOrder baseUserOrder) {
        UserVipOrder entity=(UserVipOrder) baseUserOrder;
        Date now= DateUtil.beginOfDay(new Date());
        SysUserInfo sysUserInfo = sysUserInfoService.selectByUserId(entity.getUserId());
        ApiResponse apiResponse = vipLeverService.get(request.getHeader("Authorization"),entity.getVipLevelId());
        Map data = (Map) apiResponse.getData();

        sysUserInfo.setIsVip(PayConstants.VipState.VIP);
        Date vipBeginTime = sysUserInfo.getVipBeginTime();
        if(Objects.isNull(vipBeginTime)){
            sysUserInfo.setVipBeginTime(now);
        }
        Integer vipEffectiveDay = sysUserInfo.getVipEffectiveDay();
        Date vipEndTime = sysUserInfo.getVipEndTime();
        // 如果不是会员，或者会员已过期
        if(Objects.isNull(vipEndTime) || vipEndTime.before(now)){
            vipEffectiveDay=(Integer) data.get("day");
            sysUserInfo.setVipEndTime(DateUtil.offsetDay(now,vipEffectiveDay));
        }else{
            // 如果已经是会员
            vipEffectiveDay+=(Integer) data.get("day");
            sysUserInfo.setVipEndTime(DateUtil.offsetDay(vipEndTime,vipEffectiveDay));
        }
        sysUserInfo.setVipEffectiveDay(vipEffectiveDay);

        sysUserInfoService.updateById(sysUserInfo);
    }

}

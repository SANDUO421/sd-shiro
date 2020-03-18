package com.yulin.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulin.library.entity.SysUserInfo;
import com.yulin.library.mapper.SysUserInfoMapper;
import com.yulin.library.mybatis.service.AbstractService;
import com.yulin.library.service.SysUserInfoService;
import com.yulin.library.util.jwt.JwtUtils;
import com.yulin.library.util.pay.PayConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Service
public class SysUserInfoServiceImpl extends AbstractService<SysUserInfoMapper, SysUserInfo> implements SysUserInfoService {

    @Autowired
    private JwtUtils jwtUtils;

    @Transactional
    @Override
    public SysUserInfo selectByUserId(String id) {
        SysUserInfo sysUserInfo=new SysUserInfo();
        sysUserInfo.setUserId(id);
        Wrapper<SysUserInfo> wrapper=new QueryWrapper(sysUserInfo);
        SysUserInfo result = baseMapper.selectOne(wrapper);

        if(Objects.isNull(result)){
            result=createSysUserInfo(sysUserInfo);
        }

        return result;
    }

    private SysUserInfo createSysUserInfo(SysUserInfo sysUserInfo) {
        sysUserInfo.setReadTime(0L);
        sysUserInfo.setIsVip(PayConstants.VipState.NOT_VIP);
        sysUserInfo.setRemainingBalance(new BigDecimal(0));
        sysUserInfo.setVersion(0L);
        sysUserInfo.setCreateTime(new Date());
        sysUserInfo.setCreateUserId(jwtUtils.getUserId());
        baseMapper.insert(sysUserInfo);
        return sysUserInfo;
    }
}

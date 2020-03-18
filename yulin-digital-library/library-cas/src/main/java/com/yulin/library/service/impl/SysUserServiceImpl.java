package com.yulin.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulin.library.entity.SysUser;
import com.yulin.library.mapper.SysUserMapper;
import com.yulin.library.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public SysUser loginSelect(String username) {
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.<SysUser>lambdaQuery().eq(SysUser::getPhone, username);
        SysUser sysUser = baseMapper.selectOne(wrapper);
        if(Objects.nonNull(sysUser)){
            sysUser.setPermissionList(baseMapper.getPermission(sysUser.getId()));
        }
        return sysUser;
    }
}

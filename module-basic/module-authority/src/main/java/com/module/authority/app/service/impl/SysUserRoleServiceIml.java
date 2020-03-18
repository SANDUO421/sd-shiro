package com.module.authority.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.module.authority.app.entity.SysUserRole;
import com.module.authority.app.mapper.SysUserRoleMapper;
import com.module.authority.app.service.ISysUserRoleService;

import java.util.List;

/**
 * 用户角色关系服务类
 * @author 三多
 * @Time 2020/3/1
 */
public class SysUserRoleServiceIml extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    /**
     * 根据用户id查询用户角色关系
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysUserRole> selectUserRoleListByUserId(Integer userId) {
        return baseMapper.selectUserRoleListByUserId(userId);
    }
}

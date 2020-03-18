package com.module.authority.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.module.authority.app.entity.SysUserRole;

import java.util.List;

/**
 * @description: 用户角色表 服务类
 * @author: sanduo
 * @date: 2020/3/1 16:21
 * @version: 1.0
 */
public interface ISysUserRoleService extends IService<SysUserRole> {


    /**
     * 根据用户id查询用户角色关系
     *
     * @param userId
     * @return
     */
    List<SysUserRole> selectUserRoleListByUserId(Integer userId);
}

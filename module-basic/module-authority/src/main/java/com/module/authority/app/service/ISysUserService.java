package com.module.authority.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.module.authority.app.domain.UserDTO;
import com.module.authority.app.entity.SysUser;

import java.util.List;
import java.util.Set;

/**
 * <p>用户服务类</p>
 * @author 三多
 * @Time 2020/3/1
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 注册用户
     * @param userDTO 用户实体
     * @return 返回
     */
    boolean register(UserDTO userDTO);

    /**
     * 第三方账号信息与系统账号进行绑定
     * @param sysUser 系统账户
     * @return boolean
     */
    boolean doPostSignUp(SysUser sysUser);

    /**
     * 账户密码登录
     * @param username 用户名
     * @param password 密码
     * @return String
     */
    String login(String username, String password);

    /**
     * 保存用户以及角色部门等信息
     * @param userDto
     * @return
     */
    boolean insertUser(UserDTO userDto);

    /**
     * 查询用户集合
     * @param page 分页对象
     * @param userDto 参数列表
     * @return
     */
    IPage<SysUser> getUsersWithRolePage(Page page, UserDTO userDto);

    /**
     * 更新用户以及角色部门等信息
     * @param userDto
     * @return
     */
    boolean updateUser(UserDTO userDto);

    /**
     * 通过用户名查找用户个人信息
     * @param username 用户名
     * @return 用户信息
     */
    SysUser findByUserInfoName(String username);

    /**
     * 根据用户id查询权限
     * @param userId
     * @return
     */
    Set<String> findPermsByUserId(Integer userId);

    /**
     * 通过用户去查找用户(id/用户名/手机号)
     * @param sysUser
     * @return
     */
    SysUser findSecurityUserByUser(SysUser sysUser);

    /**
     * 通过用户id查询角色集合
     * @param userId
     * @return
     */
    Set<String> findRoleIdByUserId(Integer userId);
}

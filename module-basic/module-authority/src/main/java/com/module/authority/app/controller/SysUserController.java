package com.module.authority.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.authority.app.domain.UserDTO;
import com.module.authority.app.service.ISysUserService;
import com.module.common.utils.R;
import com.module.log.app.annotation.SysOperaLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户表 前端控制器
 * @author 三多
 * @Time 2020/3/4
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    /**
     * 保存用户包括角色和部门
     * @param userDto
     * @return
     */
    @SysOperaLog(description = "保存用户包括角色和部门")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public R insert(@RequestBody UserDTO userDto){

        return R.ok(userService.insertUser(userDto));
    }

    /**
     * 查询用户集合
     * @param page
     * @param userDto
     * @return
     */
    @SysOperaLog(description = "查询用户集合")
    @GetMapping
    @PreAuthorize("hasAuthority('sys:user:view')")
    public R getList(Page page,UserDTO userDto){
        return R.ok(userService.getUsersWithRolePage(page,userDto));
    }

    /**
     * 更新用户包括角色和部门
     * @param userDto
     * @return
     */
    @SysOperaLog(description = "更新用户包括角色和部门")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:user:update')")
    public R update(@RequestBody UserDTO userDto){
        return R.ok(userService.updateUser(userDto));
    }

    /**
     * 删除用户包括角色和部门
     * @param userId
     * @return
     */
    @SysOperaLog(description = "根据用户id删除用户包括角色和部门")
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public R delete(@PathVariable("userId") Integer userId ){
        return null;
    }

    /**
     * 重置密码
     * @param userId
     * @return
     */
    @SysOperaLog(description = "重置密码")
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('sys:user:rest')")
    public R restPass(@PathVariable("userId") Integer userId ){
        return null;
    }

    /**
     * 获取个人信息
     * @return
     */
    @SysOperaLog(description = "获取个人信息")
    @GetMapping("/info")
    public R getUserInfo(){
        return null;
    }

    /**
     * 修改密码
     * @param oldPass
     * @param newPass
     * @return
     */
    @SysOperaLog(description = "修改密码")
    @PutMapping("updatePass")
    @PreAuthorize("hasAuthority('sys:user:updatePass')")
    public R updatePass(@RequestParam String oldPass,@RequestParam String newPass){
        return null;
    }

    /**
     * 检测用户名是否存在 避免重复
     * @return
     */
    @PostMapping("/vailUserName")
    public R vailUserName(@RequestParam String userName){
        return null;
    }

    /**
     * 修改邮箱
     * @param email
     * @param code
     * @param request
     * @return
     */
    @SysOperaLog(description = "修改邮箱")
    @PutMapping("updateEmail")
    @PreAuthorize("hasAuthority('sys:user:updateEmail')")
    public  R updateEmail(@RequestParam String email, @RequestParam String code, HttpServletRequest request){
        return null;
    }




}

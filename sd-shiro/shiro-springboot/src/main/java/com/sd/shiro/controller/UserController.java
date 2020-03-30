package com.sd.shiro.controller;

import com.sd.shiro.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.MD5;

import javax.annotation.Resource;

/**
 * 用户表(PeUser)表控制层
 *
 * @author SanDuo
 * @since 2020-03-30 20:12:00
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private IUserService userService;

    /**
     * 添加
     *
     * @param id 主键
     * @return 单条数据
     */
    @PostMapping("/add")
    public String add(String id) {
        return "用户添加成功";
    }
    @GetMapping("/find")
    public String find() {
        return "查询用户成功";
    }
    @GetMapping("/update/{id}")
    public String update(String id) {
        return "用户更新成功";
    }

    /**
     * 1. 传统登录
     *       前端发送请求->接口获取用户名密码->程序员在接口部门手动控制
     *
     * 2. shiro登录
     *      前端发送请求->接口获取用户名密码->通过subject.login()->realm域的认证方法
     *          a 构造登录令牌
     *          b 获取subject
     *          c 调用subject的登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String login(String username,String password) {
        try{
            /**
             * 密码加密：
             *      shiro密码加密：md5
             *      Md5Hash：
             *          参数一：加密的内容
             *          参数二：盐（加密混淆字符串）（用户的登录名）
             *          参数三：加密次数
             */
            password = new Md5Hash(username, password, 3).toString();

            //a 构造登录令牌
            UsernamePasswordToken token = new UsernamePasswordToken(username,password,true);
            //b 获取subject
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            return "登录成功";
        }catch (Exception ex){
            return "用户名或者密码错误！";
        }

    }

}
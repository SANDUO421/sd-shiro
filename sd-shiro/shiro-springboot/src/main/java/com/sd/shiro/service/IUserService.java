package com.sd.shiro.service;

import com.sd.shiro.domain.entity.User;

import java.util.List;

/**
 * @author 三多
 * @Time 2020/3/30
 */
public interface IUserService {

    public User findByUsername(String name);

    public List<User> findAll();
}

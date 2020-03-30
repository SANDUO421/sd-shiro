package com.sd.shiro.service.impl;

import com.sd.shiro.dao.UserDao;
import com.sd.shiro.domain.entity.User;
import com.sd.shiro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户表(PeUser)表服务实现类
 *
 * @author SanDuo
 * @since 2020-03-30 19:59:28
 */
@Service("peUserService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String name) {
        return userDao.findByUsername(name);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
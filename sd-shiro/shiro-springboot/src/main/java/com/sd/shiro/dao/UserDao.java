package com.sd.shiro.dao;

import com.sd.shiro.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 用户表(User)表数据库访问层
 *
 * @author SanDuo
 * @since 2020-03-30 19:53:14
 */
@Repository
public interface UserDao extends JpaRepository<User,String> , JpaSpecificationExecutor<String> {
    /**
     * 根据用户名获取信息
     * @param name
     * @return
     */
    User findByUsername(String name);


}
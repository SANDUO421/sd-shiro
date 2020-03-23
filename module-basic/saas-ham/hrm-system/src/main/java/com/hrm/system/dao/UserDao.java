package com.hrm.system.dao;

import com.hrm.domain.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 三多
 * @Time 2020/3/23
 */
@Repository
public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
}

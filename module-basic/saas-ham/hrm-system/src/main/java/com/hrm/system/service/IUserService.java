package com.hrm.system.service;

import com.hrm.domain.system.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author 三多
 * @Time 2020/3/23
 */
public interface IUserService {
    /**
     * 根据公司ID查询部门列表
     * 1. 构造查询条件
     * 2. 查询
     * 3. 参数:map集合的形式
     *          hasDept
     *          departmentId
     *          companyId
     *
     * @param map 参数
     * @param page 当前页
     * @param pageSize 页数
     * @return 用户列表
     */
    public Page findAll(Map map, int page, int pageSize);

    /**
     * 保存
     * @param User 部门
     * @return int
     */
    User save(User User);

    /**
     * 查询
     * @param id 部门id
     * @return 部门
     */
    User findById(String id);

    /**
     * 修改
     * @param User
     */
    void update(User User);

    /**
     * 删除
     * @param id
     */
    void delete(String id);
}

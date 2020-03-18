package com.hrm.company.service;

import com.hrm.domain.company.entity.Department;

import java.util.List;

/**
 * @author 三多
 * @Time 2020/3/18
 */
public interface IDepartmentService {
    /**
     * 根据公司ID查询部门列表
     * @param companyId 公司ID
     * @return 部门列表
     */
    public List<Department> findAll(String companyId);

    /**
     * 保存
     * @param department 部门
     * @return int
     */
    Department save(Department department);

    /**
     * 查询
     * @param id 部门id
     * @return 部门
     */
    Department findById(String id);

    /**
     * 修改
     * @param department
     */
    void update(Department department);

    /**
     * 删除
     * @param id
     */
    void delete(String id);
}

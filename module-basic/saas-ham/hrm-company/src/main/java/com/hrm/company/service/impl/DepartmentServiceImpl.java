package com.hrm.company.service.impl;

import com.hrm.common.service.BaseService;
import com.hrm.company.dao.DepartmentDao;
import com.hrm.company.service.IDepartmentService;
import com.hrm.domain.company.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author 三多
 * @Time 2020/3/18
 */
@Service
public class DepartmentServiceImpl extends BaseService implements IDepartmentService {
    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 根据公司ID查询部门列表
     * 1. 构造查询条件
     * 2. 查询
     *
     * @param companyId 公司ID
     * @return 部门列表
     */
    @Override
    public List<Department> findAll(String companyId) {

        Specification<Department> spec = new Specification<Department>() {
            /**
             * 构造查询条件
             *      1. 只查询companyId
             *      2. 很多的地方都需要companyId查询
             *      3. 很多的对象都具有companyId
             * @param root ：包含所有的对象数据
             * @param query ：一般不使用，用户高级查询
             * @param criteriaBuilder ：构造查询条件
             * @return Predicate
             */
            @Override
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("companyId").as(String.class), companyId);
            }
        };

        /*return departmentDao.findAll((root,query,criteriaBuilder)->{
            return criteriaBuilder.equal(root.get("companyId").as(String.class),companyId);
        });*/
        return departmentDao.findAll(getSpec(companyId));
    }

    /**
     * 保存
     *
     * @param department 部门
     * @return int
     */
    @Override
    public Department save(Department department) {
        Department entity = departmentDao.save(department);
        return entity;
    }

    /**
     * 查询
     *
     * @param id 部门id
     * @return 部门
     */
    @Override
    public Department findById(String id) {
        return departmentDao.findById(id).get();
    }

    /**
     * 修改
     *
     * @param department
     */
    @Override
    public void update(Department department) {
        departmentDao.save(department);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        departmentDao.deleteById(id);
    }
}

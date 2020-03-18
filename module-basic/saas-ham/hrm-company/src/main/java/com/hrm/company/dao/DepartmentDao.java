package com.hrm.company.dao;

import com.hrm.domain.company.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 三多
 * @Time 2020/3/18
 */
@Repository
public interface DepartmentDao extends JpaRepository<Department,String>,JpaSpecificationExecutor<Department> {
}

package com.hrm.company.dao;

import com.hrm.domain.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 三多
 * @Time 2020/3/11
 */
@Repository
public interface CompanyDao extends JpaRepository<Company,String>, JpaSpecificationExecutor<Company> {

}

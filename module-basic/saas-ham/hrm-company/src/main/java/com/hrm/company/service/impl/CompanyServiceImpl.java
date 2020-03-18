package com.hrm.company.service.impl;

import com.hrm.common.exception.BusinessException;
import com.hrm.common.utils.IdWorker;
import com.hrm.company.dao.CompanyDao;
import com.hrm.company.service.ICompanyService;
import com.hrm.domain.company.entity.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 三多
 * @Time 2020/3/11
 */
@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存企业
     * 1. 配置IdWorker
     * 2. 在service中注入IdWorker
     * 3. 通过IdWorker生成Id
     * 4. 保存企业实体
     */
    @Override
    public void add(Company company) {
        //基本属性的设置
        company.setId(String.valueOf(idWorker.nextId()));
        company.setCreateTime(new Date());
        //默认的状态
        //0：是未审核，1：是已审核
        company.setAuditState("0");
        //0：是激活，1：是未激活
        company.setState(1);
        companyDao.saveAndFlush(company);
    }

    /**
     * 更新企业
     * 1. 参数： company
     * 2. 根据ID查询，赋值企业对象
     * 3. 设置修改属性
     * 4. 调用dao完成更新
     */
    @Override
    public void update(Company company) {
        //2. 根据ID查询，赋值企业对象
        Company entity = this.findCompanyById(company.getId());
        BeanUtils.copyProperties(company, entity);
        companyDao.save(entity);
    }

    /**
     * 删除企业
     * 1. 参数 Id
     */
    @Override
    public void deleteById(String id) {
        companyDao.deleteById(id);
    }

    /**
     * 根据id查询企业
     * 1. 参数：id
     * 2. 根据DI查询Company
     * 3. 返回值 Company
     */
    @Override
    public Company findCompanyById(String id) {
        //2. 根据DI查询Company
        Company company = companyDao.findById(id).get();
        return company;
    }

    /**
     * 查询企业列表
     * 1. 返回List<Company>
     */
    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

}

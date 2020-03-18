package com.hrm.company.service;

import com.hrm.domain.company.entity.Company;

import java.util.List;

/**
 * @author 三多
 * @Time 2020/3/11
 */
public interface ICompanyService {
    /**
     * 保存企业
     *  1. 配置IdWorker
     *  2. 在service中注入IdWorker
     *  3. 通过IdWorker生成Id
     *  4. 保存企业实体
     */
    public void add(Company company);
    /**
     * 更新企业
     *  1. 参数： company
     *  2. 根据ID查询，赋值企业对象
     *  3. 设置修改属性
     *  4. 调用dao完成更新
     */
    public void update(Company company);

    /**
     * 删除企业
     *  1. 参数 Id
     */
    public void deleteById(String id);


    /**
     * 根据id查询企业
     *  1. 参数：id
     *  2. 根据DI查询Company
     *  3. 返回值 Company
     */
    public Company findCompanyById(String id);

    /**
     * 查询企业列表
     *  1. 返回List<Company>
     */
    public List<Company> findAll();
}

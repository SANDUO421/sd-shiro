package com.hrm.domain.company.vo;

import com.hrm.domain.company.entity.Company;
import com.hrm.domain.company.entity.Department;
import lombok.Data;

import java.util.List;

/**
 * 部门响应模型
 * @author 三多
 * @Time 2020/3/18
 */
@Data
public class DeptListVo {
    /**公司Id*/
    private String companyId;
    /**公司名称*/
    private String companyName;
    /**公司负责人*/
    private String companyManager;
    /**部门列表*/
    private List<Department> departs;

    /**
     * 构造返回对象
     * @param company 公司对象
     * @param departs 部门列表
     */
    public DeptListVo(Company company,List<Department> departs){
        this.companyId = company.getId();
        this.companyName = company.getName();
        //公司法人
        this.companyManager= company.getLegalRepresentative();
        this.departs = departs;
    }
}

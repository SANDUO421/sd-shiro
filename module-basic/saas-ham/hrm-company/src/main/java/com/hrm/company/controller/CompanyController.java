package com.hrm.company.controller;

import com.hrm.common.controller.BaseController;
import com.hrm.common.entity.Result;
import com.hrm.common.entity.ResultCode;
import com.hrm.company.service.ICompanyService;
import com.hrm.domain.company.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @CrossOrigin: 解决跨域
 * @author 三多
 * @Time 2020/3/12
 */
@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyController extends BaseController {
    @Autowired
    private ICompanyService companyService;

    /**
     * 保存企业
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody Company company){
        company.setCreateTime(createTime);
        companyService.add(company);
        return  new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据Id更新企业
     * 1.方法
     * 2.请求参数
     * 3.响应
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id,@RequestBody Company company){
        company.setId(id);
        companyService.update(company);
        return  new Result(ResultCode.SUCCESS);
    }
    /**
     * 根据ID删除企业
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id){
        companyService.deleteById(id);
        return  new Result(ResultCode.SUCCESS);
    }
    /**
     * 根据ID查询企业
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "id") String id){
        Company company = companyService.findCompanyById(id);
        return new Result(ResultCode.SUCCESS,company);
    }
    /**
     * 查询全部企业列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Company> list = companyService.findAll();
        return new Result(ResultCode.SUCCESS,list);
    }
}

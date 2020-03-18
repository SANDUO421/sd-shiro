package com.hrm.company.controller;

import com.hrm.common.controller.BaseController;
import com.hrm.common.entity.Result;
import com.hrm.common.entity.ResultCode;
import com.hrm.common.utils.IdWorker;
import com.hrm.company.service.ICompanyService;
import com.hrm.company.service.IDepartmentService;
import com.hrm.domain.company.entity.Company;
import com.hrm.domain.company.entity.Department;
import com.hrm.domain.company.vo.DeptListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 三多
 * @Time 2020/3/18
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/department")
public class DepartmentController extends BaseController {

    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IdWorker idWorker;

    /**
     * 保存
     * @param department
     * @return
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody  Department department){
        //设置企业Id
        department.setId(String.valueOf(idWorker.nextId()));
        department.setCreateTime(createTime);
        department.setCompanyId(companyId);
        departmentService.save(department);
        return Result.SUCCESS();
    }

    /**
     * 根据companyId查询部门
     * @return code,message,data
     *
     */
    @GetMapping(value = "/list")
    public Result findAll(){
        //1.查询
        List<Department> deptList = departmentService.findAll(companyId);
        //2. 构造返回结构
        Company company = companyService.findCompanyById(companyId);
        DeptListVo data = new DeptListVo(company,deptList);
        return new Result(ResultCode.SUCCESS,data);
    }

    /**
     * 根据部门Id查询部门
     * @param id 部门id
     * @return 实体对象
     */
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable(value = "id") String id){
        Department department = departmentService.findById(id);
        return  new Result(ResultCode.SUCCESS,department);
    }

    /**
     * 修改
     * @param department
     * @return
     */
    @PostMapping(value = "/update")
    public Result update(@RequestBody Department department){
        departmentService.update(department);
        return Result.SUCCESS();
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id") String id){
        departmentService.delete(id);
        return Result.SUCCESS();
    }

}

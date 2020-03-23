package com.hrm.system.controller;

import com.hrm.common.controller.BaseController;
import com.hrm.common.entity.PageResult;
import com.hrm.common.entity.Result;
import com.hrm.common.entity.ResultCode;
import com.hrm.common.utils.IdWorker;
import com.hrm.domain.system.entity.User;
import com.hrm.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 三多
 * @Time 2020/3/23
 */
@CrossOrigin
@RestController
@RequestMapping("/system")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存
     * @param user
     * @return
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody User user){
        //设置企业Id
        user.setId(String.valueOf(idWorker.nextId()));
        user.setCreateTime(createTime);
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        userService.save(user);
        return Result.SUCCESS();
    }

    /**
     * 根据companyId查询用户
     * @return code,message,data
     *
     */
    @GetMapping(value = "/list")
    public Result findAll(int page, int pageSize, @RequestParam Map map){
        //1.获取当前企业Id
        map.put("companyId",companyId);
        //2.查询
        Page<User> pageUser = userService.findAll(map,page,pageSize);
        //3. 构造返回结构
        PageResult pageResult = new PageResult(pageUser.getTotalElements(),pageUser.getContent());
        return new Result(ResultCode.SUCCESS,pageResult);
    }

    /**
     * 根据部门Id查询用户
     * @param id 用户Id
     * @return 实体对象
     */
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable(value = "id") String id){
        User user = userService.findById(id);
        return  new Result(ResultCode.SUCCESS,user);
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @PostMapping(value = "/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.SUCCESS();
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id") String id){
        userService.delete(id);
        return Result.SUCCESS();
    }
}

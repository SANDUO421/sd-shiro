package com.hrm.system.controller;

import com.hrm.common.controller.BaseController;
import com.hrm.common.entity.PageResult;
import com.hrm.common.entity.Result;
import com.hrm.common.entity.ResultCode;
import com.hrm.common.entity.SaasUserLevelEnum;
import com.hrm.common.exception.BusinessException;
import com.hrm.common.utils.IdWorker;
import com.hrm.common.utils.JwtUtils;
import com.hrm.domain.system.entity.Permission;
import com.hrm.domain.system.entity.User;
import com.hrm.domain.system.vo.ProfileResult;
import com.hrm.system.service.IPermissionService;
import com.hrm.system.service.IUserService;
import com.sun.javafx.logging.PulseLogger;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    private IPermissionService permissionService;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private JwtUtils jwtUtils;

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
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id") String id){
        userService.delete(id);
        return Result.SUCCESS();
    }

    /**
     * 用户登录
     *      1.根据mobile查询用户
     *      2.比较password
     *      3.生成token
     *      4.返回
     * @param mobile
     * @param password
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(String mobile,String password){
        //1.根据mobile查询用户
        User user = userService.findByMobile(mobile);
        // 2.比较password
        if(Objects.isNull(user) || ! user.getPassword().equals(password)){
            //登陆失败
            return new Result(ResultCode.MOBILE_ERROR_OR_PASSWORD_ERROR);
        }
        //3.登录成功,生成token
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("mobile",mobile);
        params.put("companyId",user.getCompanyId());
        params.put("companyName",user.getCompanyName());
        String token  = jwtUtils.createJwt(user.getId(), user.getUsername(), params);
        return  new Result(token);
    }

    /**
     *
     *
     * 用户登录成功获取用户信息
     *      1. 获取用户ID
     *             (1)从请求头信息中，获取token ：名称=Authorization
     *             (2)替换Bearer+空格
     *             (3)解析token
     *             (4)获取用户claims
     *      2. 根据用户ID查询用户
     *      3. 构造返回值类型
     *      4. 响应
     * @return
     */
    @PostMapping(value = "/profile")
    public Result profile(){
        /*//1. 获取用户ID

        //(1)从请求头信息中，获取token ：名称=Authorization
        String authorization = request.getHeader("Authorization");
        if(StringUtils.isBlank(authorization)){
            throw new BusinessException(ResultCode.UN_AUTHENTICATED);
        }
        //(2)替换Bearer+空格
        String token = authorization.replace("Bearer ", "");
        //(3)解析token*/
        //Claims claims = (Claims)request.getAttribute("user_claims");
        String userId = claims.getId();
        User user = userService.findById(userId);
        /**
         * 更具不同的用户级别获取用户权限
         *      a. saasAdmin: saas管理员具备的平台的所有权限
         *      b. coAdmin：企业管理员（创建租户企业的时候添加），具有企业的所有权限
         *      c. user：普通用户（需要分配角色），企业用户具有当前角色的权限
         */
        ProfileResult result = null;
        if(SaasUserLevelEnum.USER.getCode().equals(user.getLevel())){
            // c. user：普通用户（需要分配角色），企业用户具有当前角色的权限
            result = new ProfileResult(user);
        }else{
            Map map = new HashMap();
            if(SaasUserLevelEnum.CO_ADMIN.getCode().equals(user.getLevel())) {
                // b. coAdmin：企业管理员（创建租户企业的时候添加），具有企业的所有权限
                map.put("enVisible","1");
            }
            // a. saasAdmin: saas管理员具备的平台的所有权限
            List<Permission> list =  permissionService.findAll(map);
            result = new ProfileResult(user,list);
        }

        //4. 响应
        return new Result(result);
    }

}

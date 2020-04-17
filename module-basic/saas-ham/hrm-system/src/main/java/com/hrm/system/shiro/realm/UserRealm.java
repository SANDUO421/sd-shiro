package com.hrm.system.shiro.realm;

import com.hrm.common.entity.SaasUserLevelEnum;
import com.hrm.common.exception.BusinessException;
import com.hrm.common.shiro.realm.IhrmRealm;
import com.hrm.domain.system.entity.Permission;
import com.hrm.domain.system.entity.User;
import com.hrm.domain.system.vo.ProfileResult;
import com.hrm.system.service.IPermissionService;
import com.hrm.system.service.IUserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统微服务realm域
 *
 * @author 三多
 * @Time 2020/4/15
 */
public class UserRealm extends IhrmRealm {
    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService permissionService;

    /**
     * 重写认证方法
     * 1、获取手机号和密码
     * 2、使用手机号查询用户
     * 3、判断登录密码和数据库是否一致
     * 4、构造安全认证数据返回(安全数据，用户基本数据+权限信息，ProfileResult)
     *
     * @param passwordToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken passwordToken) throws AuthenticationException {
        //1、获取手机号和密码
        UsernamePasswordToken token = (UsernamePasswordToken) passwordToken;
        String mobile = token.getUsername();
        String password = String.valueOf(token.getPassword());
        //2、使用手机号查询用户
        User user = userService.findByMobile(mobile);
        //3、判断登录密码和数据库是否一致
        if (ObjectUtils.isEmpty(user) || !password.equals(user.getPassword())) {
            throw new BusinessException("用户名或者密码不正确！");
        }
        ProfileResult result = null;
        if(SaasUserLevelEnum.USER.getCode().equals(user.getLevel())){
            // 普通用户（需要分配角色），企业用户具有当前角色的权限
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
        //4、构造安全认证数据返回:安全数据、密码、realm域名称
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(result,user.getPassword(),getName());
        return info;
    }
}

package com.hrm.common.shiro.realm;

import com.hrm.domain.system.vo.ProfileResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

/**
 * 公共的realm 域获取安全数据，构造权限
 * @author 三多
 * @Time 2020/4/15
 */
public class IhrmRealm extends AuthorizingRealm {
    @Override
    public void setName(String name) {
        super.setName("ihrmRealm");
    }

    /**
     * 授权
     *      1、获取安全数据
     *      2、构造权限信息
     *      3、构造权限数据
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1、获取安全数据
        ProfileResult  profileResult = (ProfileResult)principals.getPrimaryPrincipal();
        //2、构造权限信息,对接口进行授权
        Set<String> apis  = (Set<String>)profileResult.getRoles().get("apis");
        //3、构造权限数据
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(apis);
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return null;
    }
}

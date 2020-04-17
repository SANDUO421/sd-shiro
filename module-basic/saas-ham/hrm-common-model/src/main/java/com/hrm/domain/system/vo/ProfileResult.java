package com.hrm.domain.system.vo;

import com.hrm.domain.system.entity.Permission;
import com.hrm.domain.system.entity.Role;
import com.hrm.domain.system.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户信息返回对象
 *      整合redis时候需要存储到redis
 *
 * @author 三多
 * @Time 2020/3/24
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResult implements Serializable, AuthCachePrincipal {
    /**
     * 移动电话
     */
    private String mobile;
    /**
     * 用户名
     */
    private String username;
    /**
     * 企业Id
     */
    private String companyId;
    /**
     * 企业名称
     */
    private String companyName;
    /**
     * roles:{
     * menus:[],
     * points:[],
     * apis:[]
     * }
     */
    private Map<String, Object> roles = new HashMap<>();

    /**
     * 构造权限结果集
     *
     * @param user
     * @param list
     */
    public ProfileResult(User user, List<Permission> list) {
        this.mobile = user.getMobile();
        this.companyName = user.getCompanyName();
        this.companyId= user.getCompanyId();
        this.username = user.getUsername();
        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();
        for (Permission permission : list) {
            String code = permission.getCode();
            if (permission.getType() == PermissionEnum.MENU.getCode()) {
                menus.add(code);
            }
            if (permission.getType() == PermissionEnum.POINT.getCode()) {
                points.add(code);
            }
            if (permission.getType() == PermissionEnum.API.getCode()) {
                apis.add(code);
            }
        }
        this.roles.put("menus", menus);
        this.roles.put("points", points);
        this.roles.put("apis", apis);
    }


    /**
     * 构造返回值
     *
     * @param user
     */
    public ProfileResult(User user) {
        this.mobile = user.getMobile();
        this.companyName = user.getCompanyName();
        this.companyId = user.getCompanyId();
        this.username = user.getUsername();
        Set<Role> roles = user.getRoles();
        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();
        for (Role role : roles) {
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                String code = permission.getCode();
                if (permission.getType() == PermissionEnum.MENU.getCode()) {
                    menus.add(code);
                }
                if (permission.getType() == PermissionEnum.POINT.getCode()) {
                    points.add(code);
                }
                if (permission.getType() == PermissionEnum.API.getCode()) {
                    apis.add(code);
                }
            }
        }
        this.roles.put("menus", menus);
        this.roles.put("points", points);
        this.roles.put("apis", apis);
    }

    /**
     * 缓存到redis中的key
     * @return
     */
    @Override
    public String getAuthCacheKey() {
        return null;
    }
}

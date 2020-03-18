package com.yulin.library.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yulin.library.util.model.entity.BaseEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
public class SysUser extends BaseEntity implements UserDetails {
    private static final long serialVersionUID = 4659891787410023171L;

    private String username;
    private String nickName;
    /**
     * 性别（0-男，1-女）
     */
    private Integer sex;
    private String phone;
    private String password;
    /**
     * 头像
     */
    private String headPortraits;
    /**
     * 阅读时长（单位秒）
     */
    private Long readTime;
    /**
     * 登陆次数
     */
    private Long loginTimes;
    /**
     * 最近登录时间
     */
    private Date recentLoginTime;
    /**
     * 状态（0-正常，1-停用）
     */
    private Integer state;
    /**
     * 是否VIP（0-是，1-不是）
     */
    private Integer isVip;
    /**
     * 会员开始日期
     */
    private Date vipBeginDate;
    /**
     * 会员有效期
     */
    private Integer vipEffectiveDay;
    /**
     * 会员结束日期
     */
    private Date vipEndDate;

    @JsonDeserialize(as = HashSet.class)
    private Set<String> permissionList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(Objects.nonNull(permissionList)){
            String[] authorities=new String[permissionList.size()];
            return AuthorityUtils.createAuthorityList(permissionList.toArray(authorities));
        }
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

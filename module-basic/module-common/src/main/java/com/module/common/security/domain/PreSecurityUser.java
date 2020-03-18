package com.module.common.security.domain;

import com.module.common.security.utils.LoginType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户手机号和账号密码 身份权限认证类 登陆身份认证
 *
 * @author 三多
 * @Time 2020/3/2
 */
@Setter
@Getter
@Accessors(chain = true)
public class PreSecurityUser implements UserDetails {
    private LoginType loginType = LoginType.NORMAL;

    private Integer userId;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public PreSecurityUser(LoginType loginType, Integer userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.loginType = loginType;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * 返回分配给用户的角色列表
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 账户是否未过期,过期无法验证
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 标记用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 标记是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

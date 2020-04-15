package com.sd.shiro.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.crazycake.shiro.AuthCachePrincipal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户表(PeUser)实体类
 *
 *      AuthCachePrincipal：
 *              redis shiroc插件包：存储会有key
 *
 * @author SanDuo
 * @since 2020-03-30 19:10:06
 */
@Entity
@Table(name="pe_user")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable , AuthCachePrincipal {


    /**
    * 主键
    */
    @Id
    private String id;
    /**
    * 用户名称
    */
    private String username;
    /**
    * 密码
    */
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="pe_user_role",joinColumns = {@JoinColumn(name="user_id",referencedColumnName = "id")}
            ,inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>(0);

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
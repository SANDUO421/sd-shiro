package com.sd.shiro.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
 * 角色表(PeRole)实体类
 *
 * @author SanDuo
 * @since 2020-03-30 19:10:06
 */
@Entity
@Table(name="pe_role")
@Getter
@Setter
@NoArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = -21330382441384723L;
    /**
    * 主键
    */
    @Id
    private String id;
    /**
    * 权限名称
    */
    private String name;
    /**
    * 说明
    */
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="pe_role_permission",joinColumns = {@JoinColumn(name="role_id",referencedColumnName = "id")}
        ,inverseJoinColumns = {@JoinColumn(name="permission_id",referencedColumnName = "id")})
    private Set<Permission> permissions = new HashSet<>(0);

}
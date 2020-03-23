package com.hrm.domain.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.CustomLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色管理(PeRole)实体类
 *
 * @author makejava
 * @since 2020-03-20 18:47:59
 */
@Entity
@Table(name = "pe_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Role implements Serializable {
    private static final long serialVersionUID = 794853884072934780L;
    /**
    * id
    */
    @Id
    private String id;
    /**
    * 角色名称
    */
    private String name;
    /**
    * 角色描述
    */
    private String description;
    /**
    * 企业Id
    */
    private String companyId;
    /**
     * 角色用户，多对多
     * mappedBy = "roles" 不维护中间表交由主表维护
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
    /**
     * 角色对权限 多对多
     * JoinTable：配置中间表
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "pe_role_permission",joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}
    ,inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "id")})
    private Set<Permission> permissions = new HashSet<>();
}
package com.hrm.domain.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限管理(PePremission)实体类
 *
 * @author makejava
 * @since 2020-03-23 12:28:49
 */
@Entity
@Table(name = "pe_permission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {
    private static final long serialVersionUID = 601531109854754160L;
    /**
    * id
    */
    @Id
    private String id;
    /**
    * 权限名称
    */
    private String name;
    /**
    * 权限类型: 1 菜单(菜单+按钮),2 功能(按钮),3 API
    */
    private Integer type;
    /**
    * 权限标识：非常重要的配置，都是根据这个判断
    */
    private String code;
    /**
    * 权限描述
    */
    private String description;
    /**
    * 权限父ID
     *  一个权限：
     *      菜单 ---多个按钮
     *      菜单 --- api权限
     *      按钮 --- api权限
    */
    private String pid;
    /**
     * 可见性状态 0 查询所有saas平台的最高权限，1查询企业的权限
     */
    private Integer enVisible;
    /**
     * 角色列表
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();


}
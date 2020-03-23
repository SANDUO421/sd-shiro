package com.hrm.domain.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 角色权限管理(PeRolePermission)实体类
 *
 * @author makejava
 * @since 2020-03-23 19:54:48
 */
@Entity
@Table(name = "pe_role_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -14972768851354989L;
    /**
    * id
    */
    @Id
    private String id;
    /**
    * 角色id
    */
    private String roleId;
    /**
    * 权限id
    */
    private String permissionId;

}
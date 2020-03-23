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
 * 菜单权限管理(PePermissionMenu)实体类
 *
 * @author makejava
 * @since 2020-03-23 13:00:42
 */
@Entity
@Table(name = "pe_permission_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PermissionMenu implements Serializable {
    private static final long serialVersionUID = -85171281300901122L;
    /**
    * id
    */
    @Id
    private String id;
    /**
    * 展示图标
    */
    private String menuIcon;
    /**
    * 排序号
    */
    private String menuOrder;

}
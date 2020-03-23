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
 * 权限管理(PePremission)实体类
 *
 * @author makejava
 * @since 2020-03-23 12:28:49
 */
@Entity
@Table(name = "pe_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
    * 权限类型: 1 菜单,2 功能,3 为API
    */
    private Integer type;
    /**
    * 权限标识
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


}
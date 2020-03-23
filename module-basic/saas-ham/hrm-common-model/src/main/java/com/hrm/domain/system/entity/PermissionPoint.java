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
 * 按钮权限管理(PePermissionPoint)实体类
 *
 * @author makejava
 * @since 2020-03-23 13:07:11
 */
@Entity
@Table(name = "pe_permission_point")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PermissionPoint implements Serializable {
    private static final long serialVersionUID = -75269705462882456L;
    /**
    * 主键id
    */
    @Id
    private String id;
    /**
    * 按钮代码
    */
    private String pointClass;
    /**
    * 按钮图标
    */
    private String pointIcon;
    /**
    * 按钮状态
    */
    private String pointStatus;

}
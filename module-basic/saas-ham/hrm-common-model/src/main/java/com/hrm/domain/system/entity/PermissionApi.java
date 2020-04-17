package com.hrm.domain.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * API权限管理(PePermissionApi)实体类
 *
 * @author makejava
 * @since 2020-03-23 19:54:48
 */
@Entity
@Table(name = "pe_permission_api")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PermissionApi implements Serializable {
    private static final long serialVersionUID = -26465228065030032L;
    /**
    * 主键id
    */
    @Id
    private String id;
    /**
    * 链接
    */
    private String apiUrl;
    /**
    * 请求类型
    */
    private String apiMethod;
    /**
    * 权限等级： 1 为通用接口权限,2 需校验接口权限
    */
    private String apiLevel;

}
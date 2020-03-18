package com.hrm.domain.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 部门管理(CoDepartment)实体类
 *
 * @author SanDuo
 * @since 2020-03-18 13:10:21
 */
@Entity
@Table(name = "co_department")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Department implements Serializable {
    private static final long serialVersionUID = -64932940829439878L;
    /**
    * id
    */
    @Id
    private String id;
    /**
    * 企业ID
    */
    private String companyId;
    /**
    * 父级部门Id
    */
    private String parentId;
    /**
    * 部门名称
    */
    private String name;
    /**
    * 部门编码
    */
    private String code;
    /**
    * 部门类别
    */
    private String category;
    /**
    * 负责人ID
    */
    private String managerId;
    /**
    * 负责人
    */
    private String manager;
    /**
    * 城市
    */
    private String city;
    /**
    * 介绍
    */
    private String introduce;
    /**
    * 创建时间
    */
    private Date createTime;

}
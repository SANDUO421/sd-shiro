package com.sd.shiro.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 权限表(PePermission)实体类
 *
 * @author SanDuo
 * @since 2020-03-30 19:10:05
 */
@Entity
@Table(name="pe_permission")
@Getter
@Setter
@NoArgsConstructor
public class Permission implements Serializable {
    private static final long serialVersionUID = -34788634913972322L;
    /**
    * 主键
    */
    @Id
    private String id;
    /**
    * 权限描述
    */
    private String name;
    /**
    * 权限编号
    */
    private String code;
    /**
    * 权限描述
    */
    private String description;


}
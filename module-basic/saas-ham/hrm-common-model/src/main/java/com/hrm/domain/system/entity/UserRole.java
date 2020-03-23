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
 * 用户角色管理(PeUserRole)实体类
 *
 * @author makejava
 * @since 2020-03-23 19:54:48
 */
@Entity
@Table(name = "pe_user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserRole implements Serializable {
    private static final long serialVersionUID = -12658595773293356L;
    /**
    * id
    */
    @Id
    private String id;
    /**
    * 用户id
    */
    private String userId;
    /**
    * 角色id
    */
    private String roleId;

}
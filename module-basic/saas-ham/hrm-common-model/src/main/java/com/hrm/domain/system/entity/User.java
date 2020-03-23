package com.hrm.domain.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户管理(BsUser)实体类
 *
 * @author makejava
 * @since 2020-03-20 17:59:25
 */
@Entity
@Table(name = "bs_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {
    private static final long serialVersionUID = 356108516754271208L;
    /**
    * id
    */
    @Id
    private String id;
    /**
    * 手机号码
    */
    private String mobile;
    /**
    * 用户名称
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
    * 启用状态 0 禁用,1是启用
    */
    private Integer enableState;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 部门Id
    */
    private String departmentId;
    /**
    * 入职时间
    */
    private Date timeOfEntry;
    /**
    * 聘用形式
    */
    private Integer formOfEmployment;
    /**
    * 工号
    */
    private String workNumber;
    /**
    * 管理形式
    */
    private String formOfManagement;
    /**
    * 工作城市
    */
    private String workingCity;
    /**
    * 转正时间
    */
    private Date correctionTime;
    /**
    * 在职状态  1 在职,2离职
    */
    private Integer inServiceStatus;
    /**
    * 企业Id
    */
    private String companyId;
    /**
    * 企业名称
    */
    private String companyName;
    /**
    * 部门名称
    */
    private String departmentName;
    /**
     * 用户与角色多对多
     *   @JsonIgnore 忽略json转换（不忽略会形成死循环）
     */
    @ManyToMany
    @JsonIgnore
    @JoinTable(name="pe_user_role",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();

}
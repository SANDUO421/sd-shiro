package com.hrm.domain.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 公司实体
 * @author: SanDuo
 * @date: 2020/3/11 13:32
 * @version: 1.0
 */
@Entity
@Table(name = "co_company")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @Id
    private String id;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 企业登录账号
     */
    private String managerId;
    /**
     * 当前版本
     */
    private String version;
    /**
     * 续期时间
     */
    private Date renewalDate;
    /**
     * 到期时间
     */
    private Date expirationDate;
    /**
     * 公司地区
     */
    private String companyArea;
    /**
     * 公司地址
     */
    private String companyAddress;
    /**
     * 营业执照-图片ID
     */
    private String businessLicenseId;
    /**
     * 法人代表
     */
    private String legalRepresentative;
    /**
     * 公司电话
     */
    private String companyPhone;
    /**
     * 邮箱
     */
    private String mailbox;
    /**
     * 公司规模
     */
    private String companySize;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 备注
     */
    private String remark;
    /**
     * 审核状态,0未审核 1审核
     */
    private String auditState;
    /**
     * 激活状态 0：是激活，1：未激活
     */
    @Column(name = "state",insertable = false)
    private Integer state;
    /**
     * 当前余额
     */
    private Double balance;
    /**
     * 创建时间
     */
    private Date createTime;
}
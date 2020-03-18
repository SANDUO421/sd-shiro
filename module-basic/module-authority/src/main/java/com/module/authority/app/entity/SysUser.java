package com.module.authority.app.entity;

import cn.hutool.crypto.digest.mac.MacEngine;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.module.authority.app.utils.Create;
import com.module.authority.app.utils.Update;
import com.module.common.sensitive.SensitiveInfo;
import com.module.common.sensitive.SensitiveType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @description:    用户表
 * @author:         sanduo
 * @date:           2020/3/1 14:58
 * @version:        1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

   private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @NotNull(message = "userId 不能为空")
    @Length(groups = {Update.class})
    private Integer userId;

    /**
     * 用户名
     */
    @SensitiveInfo(SensitiveType.CHINESE_NAME)
    @NotNull(message = "username 不能为空")
    @Length(groups = {Create.class})
    private String username;

   /**
     * 密码
     */
   @NotNull(message = "password 不能为空")
   @Length(groups = {Create.class})
    private String password;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 岗位ID
     */
    private Integer jobId;

   /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    @Length(groups = {Create.class})
    @SensitiveInfo(SensitiveType.EMAIL)
    @Email(message = "邮箱格式不对")
    private String email;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    @Length(groups = {Create.class})
    @SensitiveInfo(SensitiveType.MOBILE_PHONE)
    private String phone;

    /**
     * 头像
     */
    private String avatar;

   /**
     * 创建时间
     */
   @Future(message = "时间必须是将来时间", groups = {Create.class})
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Future(message = "时间必须是将来时间", groups = {Create.class})
    private LocalDateTime updateTime;

    /**
     * 0-正常，1-锁定
     */
    private String lockFlag;

    /**
     * 0-正常，1-删除
     */
    private String delFlag;

    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<SysRole> roleList;
    /**
     * 非数据库字段
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;;

    @TableField(exist = false)
    private String key;
    /**
     * 工作名称
     */
    @TableField(exist = false)
    private String jobName;


}

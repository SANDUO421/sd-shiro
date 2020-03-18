package com.module.authority.app.domain;

import com.module.authority.app.utils.Create;
import com.module.authority.app.utils.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * @description:    用户dao层操作
 * @author:         sanduo
 * @date:           2020/3/1 11:24
 * @version:        1.0
 */
@Data
public class UserDTO implements Serializable {

    /**用户ID*/
    @NotNull(message = "用户Id",groups = {Update.class})
    private Integer userId;
    /**用户名称*/
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20,message = "用户名不能超过20个字符",groups = {Create.class,Update.class})
    @Pattern(regexp = "^[\\\u4E00-\\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String username;
    /**密码*/
    private String password;
    /**部门Id*/
    private Integer deptId;
    /**电话号码*/
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误", groups = {Create.class, Update.class})
    private String phone;
    /**邮箱*/
    @Email
    private String email;
    /**图标*/
    private String avatar;
    /**锁定标识:0 正常 1锁定*/
    private String lockFlag;
    /**删除标识*/
    private String delFlag;
    /**角色列表*/
    private List<Integer> roleList;
    /**部门列表*/
    private List<Integer> deptList;
    /**
     * 新密码
     */
    private String newPassword;
    /**验证码*/
    private String smsCode;
}

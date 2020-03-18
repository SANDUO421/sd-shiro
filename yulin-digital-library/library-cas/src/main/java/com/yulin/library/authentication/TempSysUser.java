package com.yulin.library.authentication;

import com.yulin.library.util.model.entity.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class TempSysUser extends BaseEntity {
    private static final long serialVersionUID = 3284402597740393330L;

    private String username;
    private String nickName;
    /**
     * 性别（0-男，1-女）
     */
    private Integer sex;
    private String phone;
    private String password;
    /**
     * 头像
     */
    private String headPortraits;
    /**
     * 阅读时长（单位秒）
     */
    private Long readTime;
    /**
     * 登陆次数
     */
    private Long loginTimes;
    /**
     * 最近登录时间
     */
    private Date recentLoginTime;
    /**
     * 状态（0-正常，1-停用）
     */
    private Integer state;
    /**
     * 是否VIP（0-是，1-不是）
     */
    private Integer isVip;
    /**
     * 会员开始日期
     */
    private Date vipBeginDate;
    /**
     * 会员有效期
     */
    private Integer vipEffectiveDay;
    /**
     * 会员结束日期
     */
    private Date vipEndDate;

    private Set<String> permissionList;
}
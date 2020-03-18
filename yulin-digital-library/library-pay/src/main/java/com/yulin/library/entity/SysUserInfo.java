package com.yulin.library.entity;

import com.baomidou.mybatisplus.annotation.Version;
import com.yulin.library.mybatis.model.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SysUserInfo extends BaseEntity {
    private static final long serialVersionUID = 4659891787410023171L;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 阅读时长（单位秒）
     */
    private Long readTime;
    /**
     * 是否VIP（0-是，1-不是）
     */
    private Integer isVip;
    /**
     * 会员开始时间
     */
    private Date vipBeginTime;
    /**
     * 会员有效期
     */
    private Integer vipEffectiveDay;
    /**
     * 会员结束时间
     */
    private Date vipEndTime;
    /**
     * 余额
     */
    private BigDecimal remainingBalance;
    /**
     * 版本号
     */
    @Version
    private Long version;

}

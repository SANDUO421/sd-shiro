package com.yulin.library.entity;

import lombok.Data;

import java.util.Date;

/**
 * 会员充值
 * @author 星中月
 * @since 2018/7/6 0006
 */
@Data
public class UserVipOrder extends BaseUserOrder {
    private static final long serialVersionUID = -2351558032178689626L;
    /**
     * vip说明 id
     */
    private String vipLevelId;
    /**
     * 开通时间
     */
    private Date beginTime;

}

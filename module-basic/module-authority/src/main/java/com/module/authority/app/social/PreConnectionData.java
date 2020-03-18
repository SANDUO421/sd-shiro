package com.module.authority.app.social;

import lombok.Data;

import java.io.Serializable;

/**
 * <pre>第三方数据类</pre>
 * @author 三多
 * @Time 2020/3/2
 */
@Data
public class PreConnectionData implements Serializable {
    /**提供者的Id*/
    private String providerId;
    /**提供者的用户ID*/
    private String providerUserId;
    /**展示的名称*/
    private String displayName;
    /**个人介绍url*/
    private String profileUrl;
    /**图标uRL*/
    private String imageUrl;
    /**接入token*/
    private String accessToken;
    /**密钥*/
    private String secret;
    /**刷新token*/
    private String refreshToken;
    /**过期时间*/
    private Long expireTime;
}

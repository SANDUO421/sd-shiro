package com.module.authority.app.security.domian;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社交用户信息
 * @author 三多
 * @Time 2020/3/2
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialUserInfo {
    /**提供者ID*/
    private String providerId;
    /**提供者UserId*/
    private String providerUserId;
    /**别名*/
    private String nickname;
    /**头像*/
    private String headImg;
}

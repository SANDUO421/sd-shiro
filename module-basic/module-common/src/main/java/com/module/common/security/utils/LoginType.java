package com.module.common.security.utils;

import lombok.Getter;

/**
 * 登录类型 现在有用户名 短信 社交
 * @author 三多
 * @Time 2020/3/2
 */
@Getter
public enum  LoginType {
    NORMAL,SMS,SOCIAL;
}

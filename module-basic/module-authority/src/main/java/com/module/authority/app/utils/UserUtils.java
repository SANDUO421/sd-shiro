package com.module.authority.app.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 用户工具类
 * @author 三多
 * @Time 2020/3/1
 */
@UtilityClass
public class UserUtils {


    /**
     * 密码加密
     * @param rawPwd
     * @return
     */
    public String encode(String rawPwd){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPwd);
    }
}

package com.module.authority.app.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.*;

/**
 * pre系统用户工具类
 * @author 三多
 * @Time 2020/3/4
 */
@UtilityClass
public class PreUtil {

    /**
     *  生成BCryptPasswordEncoder密码
     * @param rawPass
     * @return
     */
    public String encode(String rawPass){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPass);
    }
}

package com.hrm.common.utils;

import lombok.experimental.UtilityClass;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author 三多
 * @Time 2020/4/15
 */
@UtilityClass
public class PasswordEncoderUtils {
    /**
     *
     * @param salt 盐，随机数
     * @param source 密码.密码
     * @return
     */
    public String encoder(String source,String salt){
        //加密三次
        return new Md5Hash(source, salt, 3).toString();
    }
}

package com.yulin.library.authentication;

import com.yulin.library.entity.SysUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author LIQIU
 * @date 2018-3-31
 **/
public interface IntegrationAuthenticator {

    String USERNAME_PASSWORD="usernamePassword";
    String SMS="sms";

    /**
     * 处理集成认证
     * @param username
     * @return
     */
    SysUser authenticate(String username) throws UsernameNotFoundException;


    /**
     * 进行预处理
     */
    void prepare();

     /**
     * 判断是否支持集成认证类型
     * @return
     */
    boolean support();

    /**
     * 认证结束后执行
     */
    void complete();

}

package com.yulin.library;

import cn.hutool.core.codec.Base64;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordTest {

    @Test
    public void passwordTest(){
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);
    }

    @Test
    public void base64Test(){
        String encode = Base64.encode("test:test");
        System.out.println(encode);
    }

}

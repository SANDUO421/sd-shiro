package com.yulin.library.util.jwt;

import com.alibaba.fastjson.JSONObject;
import com.yulin.library.util.ClassUtils;
import com.yulin.library.util.model.entity.CurrentUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class JwtUtils {

    @Autowired(required = false)
    private HttpServletRequest request;

    public JSONObject getClaims(){
        if(Objects.isNull(request)){
            throw new NullPointerException("servlet 容器为空");
        }
        String authorization = request.getHeader("Authorization");
        if(StringUtils.isBlank(authorization)){
            throw new NullPointerException("认证信息为空");
        }
        String token = StringUtils.substringAfter(authorization, "Bearer ");
        boolean present = ClassUtils.isPresent("org.springframework.security.jwt.JwtHelper");
        if(present){
            Jwt decode = JwtHelper.decode(token);
            return JSONObject.parseObject(decode.getClaims());
        }
        return null;
    }

    public Long getUserId(){
        return getUserInfo().getId();
    }

    public CurrentUser getUserInfo(){
        JSONObject claims = null;
        try {
            claims =  getClaims();
        } catch (Throwable t){
            System.err.println(t.getMessage());
        }
        CurrentUser currentUser = null;
        if(Objects.nonNull(claims)){
            currentUser = claims.getObject("currentUser",CurrentUser.class);
        }
        return currentUser;
    }

}

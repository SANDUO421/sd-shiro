package com.module.common.security.utils;

import com.alibaba.fastjson.JSON;
import com.module.common.exception.PreBaseException;
import com.module.common.security.domain.PreSecurityUser;
import com.module.common.utils.R;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 安全服务工具类
 *
 * @author 三多
 * @Time 2020/3/2
 */
@UtilityClass
public class SecurityUtil {


    /**
     * 响应
     *
     * @param r        返回值
     * @param response 响应对象
     * @throws IOException IO异常
     */
    public void writeJavaScript(R r, HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(r));
        printWriter.flush();
    }

    /**
     * 获取Authentication
     *
     * @return
     */
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取登录用户
     * @return
     */
    public PreSecurityUser getUser(){
        try{
            return (PreSecurityUser)getAuthentication().getPrincipal();
        }catch(Exception ex){
            throw new PreBaseException("登录状态过期", HttpStatus.UNAUTHORIZED.value());
        }
    }
}

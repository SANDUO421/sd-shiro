package com.hrm.common.controller;

import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 基础controller
 * @author 三多
 * @Time 2020/3/18
 */
public class BaseController {
    /**请求对象*/
    protected HttpServletRequest request;
    /**响应对象*/
    protected HttpServletResponse response;
    /**公司Id*/
    protected String companyId;
    /**公司名称*/
    protected String companyName;
    protected Date createTime;
    protected Claims claims;

    /**
     * 在进入controller之前那执行
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setResAndReq(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
        Object userClaims = request.getAttribute("user_claims");
        if(Objects.nonNull(userClaims) && userClaims instanceof Claims){
            this.claims = (Claims)userClaims;
        }
        //默认，后期解决
        this.companyId = String.valueOf(claims.get("companyId"));
        this.companyName = String.valueOf(claims.get("companyName"));
        this.createTime = new Date();
    }

}

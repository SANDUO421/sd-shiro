package com.hrm.common.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

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

    /**
     * 在进入controller之前那执行
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setResAndReq(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
        //默认，后期解决
        this.companyId = "1";
        this.companyName = "公众信产";
        this.createTime = new Date();
    }
}

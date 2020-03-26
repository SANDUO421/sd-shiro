package com.hrm.common.intercept;

import com.hrm.common.entity.ResultCode;
import com.hrm.common.exception.BusinessException;
import com.hrm.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 自定义拦截器：
 *     继承：
 *     HandlerInterceptorAdapter
 *      preHandle：进入到控制器之前执行的内容
 *          boolean：
 *              true：可以继续执行控制器方法
 *              false：拦截控制器方法
 *      postHandle：执行控制器方法之后执行的内容
 *      afterCompletion：响应结束之后执行的内容
 * @author 三多
 * @Time 2020/3/26
 */
@Component
public class JwtIntercept extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 1、通过拦截器获取token
     *      统一的用户权限校验(是否登录)
     *         a. 通过request获取token信息
     *         b. 从token中获取claims
     *         c. 将claims 绑定到request域中
     * 2、判断当前用户是否具有当前访问接口的权限
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //a. 通过request获取token信息
        String authorization = request.getHeader("Authorization");
        //判断请求头是否为空，或者以Bearer开头
        if(! StringUtils.isEmpty(authorization)&& authorization.startsWith("Bearer")){
            //获取token
            String token = authorization.replace("Bearer ", "");
            //解析token 获取 claims
            Claims claims = jwtUtils.parseJwt(token);
            if(Objects.nonNull(claims)){
                request.setAttribute("user_claims",claims);
                return true;
            }
        }
        throw new BusinessException(ResultCode.UN_AUTHENTICATED);

    }

}

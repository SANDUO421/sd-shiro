package com.module.authority.app.security.code.img;

import com.module.common.constant.PreConstant;
import com.module.common.exception.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 图形验证码过滤器
 * @author 三多
 * @Time 2020/3/7
 */
public class ImageCodeFilter  extends OncePerRequestFilter {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token参数
        String token = obtainToken(request);
        // 必须为/login请求和post请求
        if(StringUtils.equals("/login",request.getRequestURL()) && StringUtils.isEmpty(token) && StringUtils.endsWithIgnoreCase(request.getMethod(),"POST")){
            try{
                //1. 进行验证码的校验
                validateCode(request);
            }catch (ValidateCodeException ex){
                // 2. 如果校验不通过，调用SpringSecurity的校验失败处理器
                authenticationFailureHandler.onAuthenticationFailure(request,response,ex);
                return;
            }
            // 3. 通过放行
            filterChain.doFilter(request,response);
        }
    }

    /**
     * 验证流程
     * @param request
     */
    private void validateCode(HttpServletRequest request) {
        String captcha = obtainImageCode(request);
        String t = obtainT(request);
        //验证验证码
        if(StringUtils.isBlank(captcha)){
            throw new ValidateCodeException("验证码不能为空！");
        }
        // 从redis中获取之前保存的验证码跟前台传来的验证码进行匹配
        Object kaptcha = redisTemplate.opsForValue().get(PreConstant.PRE_IMAGE_KEY + t);
        if(Objects.isNull(kaptcha)){
            throw new ValidateCodeException("验证码已经失效");
        }
        if(! captcha.toUpperCase().equals(kaptcha)){
            throw new ValidateCodeException("验证码错误");
        }



    }

    /**
     * 获取前端传来的图片验证码
     * @param request
     * @return
     */
    private String obtainT(HttpServletRequest request) {
        String token = "key";
        return request.getParameter(token);
    }

    /**
     * 获取图片验证码
     * @param request
     * @return
     */
    private String obtainImageCode(HttpServletRequest request) {
        String imageCode = "code";
        return request.getParameter(imageCode);
    }

    /**
     * 获取前端传来的图片验证码
     * @param request
     * @return
     */
    private String obtainToken(HttpServletRequest request) {
        String token = "token";
        return request.getParameter(token);
    }

    /**
     * 失败处理器
     *
     * @param authenticationFailureHandler
     */
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

}

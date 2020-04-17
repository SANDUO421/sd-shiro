package com.hrm.system.config;

import com.hrm.common.intercept.JwtIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 三多
 * @Time 2020/3/27
 */
@Configuration
public class SysConfig implements WebMvcConfigurer {
    @Autowired
    private JwtIntercept jwtIntercept;

    /**
     * 添加拦截器配置
     *      1. 添加自定义拦截器
     *      2. 指定拦截器的URL地址
     *      3. 指定不拦截器的URL地址
     *  造成问题：
     *      延迟加载no session
     * @param registry
     */
   /* @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //1. 添加自定义拦截器
        registry.addInterceptor(jwtIntercept)
                //2. 指定拦截器的URL地址
                .addPathPatterns("/**")
                //3. 指定不拦截器的URL地址
                .excludePathPatterns("/system/login","/frame/register/**");
    }*/

    /**
     * jpa EntityManager 懒加载：解决no session
     * 防止 session失效
     *
     * 延迟关闭session到 view 层
     * @return
     */
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
        return new OpenEntityManagerInViewFilter();
    }
}

package com.hrm.system.config.shiro.config;

import com.hrm.common.shiro.realm.IhrmRealm;
import com.hrm.common.shiro.session.CustomerSessionManager;
import com.hrm.system.shiro.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author 三多
 * @Time 2020/4/2
 */
@Configuration
public class ShiroConfig {
    /**
     * 配置shiro的过滤器工厂
     *      一组过滤器集合
     *      拦截页面跳转
     * 步骤：
     *      1.创建过滤器工厂
     *      2.设置安全管理器
     *      3.通用配置（跳转登录页面，未授权跳转的页面）
     *      4.设置过滤器集合
     * 问题：web.xml 没有配置shiro相关的拦截器
     * 解决问题：
     *      org.apache.shiro.UnavailableSecurityManagerException:
     *      No SecurityManager accessible to the calling code,
     *      either bound to the org.apache.shiro.util.
     *      ThreadContext or as a vm static singleton.
     *      This is an invalid application configuration.
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager ) {
        // 1.创建过滤器工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 2.设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //3.通用配置（跳转登录页面，未授权跳转的页面）
        //跳转url
        shiroFilterFactoryBean.setLoginUrl("/authError?code=1");
        //未授权url
        shiroFilterFactoryBean.setUnauthorizedUrl("/authError?code=2");

        //4.设置过滤器集合,map集合，设置所有的过滤器有顺序：key:拦截的地址；value：过滤器类型
        LinkedHashMap<String, String> filterMap= new LinkedHashMap<>();
        /***
         * anon   匿名访问
         * authc  认证后访问
         * perms  权限才可以访问
         * */
        /***anon   匿名访问*/
        filterMap.put("/system/login","anon");
        filterMap.put("/authError","anon");
        //filterMap.put("/user/find","anon");

        /***authc  认证后访问*/
        //当前请求地址必须认证后访问
        filterMap.put("/**","authc");
        /***perms  权限才可以访问*/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建shiro安全管理器
     *      管理所有的realm
     * @return
     */
    @Bean
    public SecurityManager securityManager(IhrmRealm ihrmRealm) {
        //使用web默认的额缓存管理器，可以自定义使用外部的缓存管理器
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 配置 SecurityManager，并注入 shiroRealm
       securityManager.setRealm(ihrmRealm);
        //可选：设置自定义的会话管理器
        securityManager.setSessionManager(sessionManager());
       //可选：设置自定义的缓存管理器
        securityManager.setCacheManager(redisCacheManager());
        return securityManager;
    }

    /**
     * 创建realm
     * @return
     */
    @Bean
    public IhrmRealm shiroRealm() {
        // 配置 Realm
        return new UserRealm();
    }


    /**
     * 自定义Session会话
     *      1、redis的控制器、操作redis
     *      2、SessionDao
     *      3、会话管理器
     *      4、缓存管理器
     */
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private int database;

    /***
     * 1、redis的控制器、操作redis
     * @return
     */
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        /*GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(100);
        poolConfig.setMaxTotal(100);
        poolConfig.setMinIdle(0);
        JedisPool jedisPool = new JedisPool(poolConfig,host,port);
        redisManager.setJedisPool(jedisPool);*/
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setDatabase(database);
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * 2、SessionDao
     * @return
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 3、会话管理器
     * @return
     */
    public DefaultWebSessionManager sessionManager(){
        CustomerSessionManager sessionManager = new CustomerSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        //可以禁用cookie
        sessionManager.setSessionIdCookieEnabled(false);
        //禁用url重写 url;jsessionid=id
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return  sessionManager;
    }

    /**
     * 4、缓存管理器
     * @return
     */
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 开启对shiro注解的支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}

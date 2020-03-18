package com.module.log.app.aspect;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.module.common.security.domain.PreSecurityUser;
import com.module.common.security.utils.SecurityUtil;
import com.module.common.utils.IpUtil;
import com.module.common.utils.R;
import com.module.log.app.domain.SysLog;
import com.module.log.app.event.SysLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.apache.logging.log4j.CloseableThreadContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author 三多
 * @Time 2020/3/2
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    private ThreadLocal<SysLog> sysLogThreadLocal = new ThreadLocal<SysLog>();

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 定义controller切入点拦截规则，拦截SysLog注解的方法
     */
    @Pointcut("@annotation(com.module.log.app.annotation.SysOperaLog)")
    public  void sysLogAspect(){ }


    /**
     * 前置通知
     * @param joinpoint 切点
     */
    @Before(value = "sysLogAspect()")
    public void recordLog(JoinPoint joinpoint){
        SysLog sysLog = new SysLog();
        //将当前实体保存到threadLocal
        sysLogThreadLocal.set(sysLog);
        //开始时间
        long beginTime = Instant.now().toEpochMilli();
        HttpServletRequest request
                = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        PreSecurityUser preSecurityUser = SecurityUtil.getUser();
        sysLog.setUsername(preSecurityUser.getUsername());
        sysLog.setActionUrl(URLUtil.getPath(request.getRequestURI()));
        sysLog.setStartTime(LocalDateTime.now());
        String clientIP = ServletUtil.getClientIP(request);
        sysLog.setIp(clientIP);
        sysLog.setLocation(IpUtil.getCityInfo(clientIP));
        sysLog.setRequestMethod(request.getMethod());
        String uaStr = request.getHeader("user-agent");
        sysLog.setBrowser(UserAgentUtil.parse(uaStr).getBrowser().toString());
        sysLog.setOs(UserAgentUtil.parse(uaStr).getOs().toString());
        //访问目标方法的参数 可动态改变参数值
        Object[] args = joinpoint.getArgs();
        //获取执行的方法名
        sysLog.setActionMethod(joinpoint.getSignature().getName());
        //类名
        sysLog.setClassPath(joinpoint.getTarget().getClass().getName());
        sysLog.setFinishTime(LocalDateTime.now());
        long endTime = Instant.now().toEpochMilli();
        sysLog.setConsumingTime(endTime - beginTime);
    }

    /**
     * 返回通知
     * @param ret 参数
     */
    @AfterReturning(returning = "ret",pointcut = "sysLogAspect()")
    public void doAfterReturning(Object ret){
        //得到当前线程的log对象
        SysLog sysLog = sysLogThreadLocal.get();
        //处理完请求，返回内容
        R r = Convert.convert(R.class,ret);
        if(r.getCode() == 200){
            //正常反馈
            sysLog.setType(1);
        }else{
            sysLog.setType(2);
            sysLog.setExDetail(r.getMsg());
        }
        //发布事件
        applicationContext.publishEvent(new SysLogEvent(sysLog));
        //移除当前log实体
        sysLogThreadLocal.remove();
    }





}

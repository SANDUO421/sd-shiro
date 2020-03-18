package com.module.log.app.utils;

import com.module.log.app.annotation.SysOperaLog;
import com.module.log.app.domain.SysLog;
import lombok.experimental.UtilityClass;
import org.aspectj.lang.JoinPoint;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

/**
 * 日志工具类
 *
 * @author 三多
 * @Time 2020/3/4
 */
@UtilityClass
public class LogUtil {

    /**
     * 获取操作信息
     * @param joinPoint 结点
     * @return 返回
     * @throws ClassNotFoundException 异常
     */
    public String getControllerMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        // 获取连接点目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 获取连接点签名的方法名
        String methodName = joinPoint.getSignature().getName();
        //获取连接点参数
        Object[] args = joinPoint.getArgs();
        //根据连接点类的名字获取指定类
        Class<?> targetClass = Class.forName(targetName);
        //获取类里面的方法
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                Class<?>[] parameterTypes = method.getParameterTypes();
                if(parameterTypes.length == args.length){
                    description = method.getAnnotation(SysOperaLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取堆栈信息
     * @param throwable 异常
     * @return String
     */
    public String getStackTrace(Throwable throwable){
        StringWriter writer = new StringWriter();
        try(PrintWriter pw = new PrintWriter(writer)){
            throwable.printStackTrace(pw);
            return  writer.toString();

        }
    }

}

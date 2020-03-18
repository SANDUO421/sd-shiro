package com.module.log.app.event;

import com.module.log.app.domain.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 * @author 三多
 * @Time 2020/3/4
 */
public class SysLogEvent  extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param sysLog the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public SysLogEvent(SysLog sysLog) {
        super(sysLog);
    }
}

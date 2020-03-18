package com.module.log.app.event;

import com.module.log.app.domain.SysLog;
import com.module.log.app.service.ISysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 注解形式的监听 异步监听日志事件
 * @author 三多
 * @Time 2020/3/4
 */
@Slf4j
@Component
public class SysLogListener {

    @Autowired
    private ISysLogService sysLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event){
        SysLog sysLog = (SysLog)event.getSource();
        //保存日志
        sysLogService.save(sysLog);
    }
}

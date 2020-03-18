package com.module.log.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.module.log.app.domain.SysLog;

/**
 * @author 三多
 * @Time 2020/3/4
 */
public interface ISysLogService  extends IService<SysLog> {

    /**
     *  分页查询
     * @param page 页码
     * @param pageSize 每页的显示条数
     * @param type 类型
     * @param username 用户名
     * @return  IPage<SysLog>
     */
    IPage<SysLog> selectLogList(Integer page,Integer pageSize,Integer type,String username);
}

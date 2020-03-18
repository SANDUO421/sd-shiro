package com.module.log.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.module.log.app.domain.SysLog;
import com.module.log.app.mapper.SysLogMapper;
import com.module.log.app.service.ISysLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 系统日志 服务实现类
 *
 * @author 三多
 * @Time 2020/3/4
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
    /**
     * 分页查询
     *
     * @param page     页码
     * @param pageSize 每页的显示条数
     * @param type     类型
     * @param username 用户名
     * @return IPage<SysLog>
     */
    @Override
    public IPage<SysLog> selectLogList(Integer page, Integer pageSize, Integer type, String username) {
        Page<SysLog> logPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<SysLog> queryWrapper = new QueryWrapper<SysLog>().lambda()
                .eq(SysLog::getType, type)
                .orderByDesc(SysLog::getStartTime);
        if (StringUtils.isNoneEmpty(username)) {
            queryWrapper.like(SysLog::getUsername, username);
        }
        return baseMapper.selectPage(logPage, queryWrapper);
    }
}

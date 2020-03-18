package com.yulin.library.service;

import com.yulin.library.entity.SysUser;
import com.yulin.library.mybatis.service.BaseService;

public interface SysUserService extends BaseService<SysUser> {
    SysUser loginSelect(String username);
}

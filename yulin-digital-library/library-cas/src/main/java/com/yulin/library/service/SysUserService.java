package com.yulin.library.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yulin.library.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    SysUser loginSelect(String username);
}

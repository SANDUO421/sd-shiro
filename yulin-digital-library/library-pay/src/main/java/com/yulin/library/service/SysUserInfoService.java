package com.yulin.library.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yulin.library.entity.SysUserInfo;
import com.yulin.library.mybatis.service.BaseService;

public interface SysUserInfoService extends BaseService<SysUserInfo> {

    SysUserInfo selectByUserId(String id);

}

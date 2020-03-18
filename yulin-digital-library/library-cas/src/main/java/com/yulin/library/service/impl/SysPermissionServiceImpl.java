package com.yulin.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulin.library.entity.SysPermission;
import com.yulin.library.mapper.SysPermissionMapper;
import com.yulin.library.service.SysPermissionService;
import org.springframework.stereotype.Service;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
}

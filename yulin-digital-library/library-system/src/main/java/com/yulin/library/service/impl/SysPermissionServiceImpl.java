package com.yulin.library.service.impl;

import com.yulin.library.entity.SysPermission;
import com.yulin.library.mapper.SysPermissionMapper;
import com.yulin.library.mybatis.service.AbstractService;
import com.yulin.library.service.SysPermissionService;
import org.springframework.stereotype.Service;

@Service
public class SysPermissionServiceImpl extends AbstractService<SysPermissionMapper, SysPermission> implements SysPermissionService {
}

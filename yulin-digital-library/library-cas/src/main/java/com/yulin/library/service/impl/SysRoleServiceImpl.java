package com.yulin.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulin.library.entity.SysRole;
import com.yulin.library.mapper.SysRoleMapper;
import com.yulin.library.service.SysRoleService;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}

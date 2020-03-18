package com.yulin.library.service.impl;

import com.yulin.library.entity.SysMenu;
import com.yulin.library.mapper.SysMenuMapper;
import com.yulin.library.mybatis.service.AbstractService;
import com.yulin.library.service.SysMenuService;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl extends AbstractService<SysMenuMapper, SysMenu> implements SysMenuService {
}

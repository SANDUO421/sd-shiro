package com.yulin.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yulin.library.entity.SysMenu;
import com.yulin.library.mapper.SysMenuMapper;
import com.yulin.library.service.SysMenuService;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
}

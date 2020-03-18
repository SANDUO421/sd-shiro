package com.module.authority.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.module.authority.app.entity.SysTenant;

/**
 * @author 三多
 * @Time 2020/3/2
 */
public interface ISysTenantService extends IService<SysTenant> {
    /**
     * 保存租户
     * @param sysTenant 租户
     * @return boolean
     */
    boolean saveTenant(SysTenant sysTenant);
}

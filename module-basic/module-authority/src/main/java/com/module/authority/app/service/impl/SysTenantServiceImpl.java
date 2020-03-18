package com.module.authority.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.module.authority.app.data.tenant.PreTenantContextHolder;
import com.module.authority.app.entity.SysDept;
import com.module.authority.app.entity.SysTenant;
import com.module.authority.app.entity.SysUser;
import com.module.authority.app.mapper.SysDeptMapper;
import com.module.authority.app.mapper.SysTenantMapper;
import com.module.authority.app.service.ISysDeptService;
import com.module.authority.app.service.ISysTenantService;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 租户service
 * @author 三多
 * @Time 2020/3/2
 */
@Service
public class SysTenantServiceImpl extends ServiceImpl<SysTenantMapper, SysTenant> implements ISysTenantService {
    @Autowired
    private SysTenantMapper sysTenantMapper;

    private ISysDeptService deptService;
    /**
     * 保存租户
     *
     * @param sysTenant 租户
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveTenant(SysTenant sysTenant) {
        this.save(sysTenant);
        // 修改租户Id 每次插入都是新的
        PreTenantContextHolder.setCurrentTenantId(Long.valueOf(sysTenant.getId()));
        /********************插入部门*************************/
        SysDept sysDept = new SysDept();
        sysDept.setName("默认部门");
        sysDept.setParentId(0);
        sysDept.setSort(0);
        deptService.save(sysDept);
        /********************构造初始化用户*************************/
        SysUser sysUser = new SysUser();
        sysUser.setUsername("root");
        sysUser.setPassword("");
        /********************插入角色*************************/

        return false;
    }
}

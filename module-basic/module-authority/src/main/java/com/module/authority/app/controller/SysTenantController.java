package com.module.authority.app.controller;

import com.module.authority.app.entity.SysTenant;
import com.module.authority.app.service.ISysTenantService;
import com.module.common.utils.R;
import com.module.log.app.annotation.SysOperaLog;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统租户管理
 * @author 三多
 * @Time 2020/3/2
 */
@Api(value = "租户管理")
@RestController
@RequestMapping("/tenant")
public class SysTenantController {

    @Autowired
    private ISysTenantService sysTenantService;

    /**
     * 租户添加
     * @param sysTenant
     * @return
     */
    @SysOperaLog(description = "新增租户")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:tenant:add')")
    public R save(@RequestBody @Validated SysTenant sysTenant) {
        return R.ok(sysTenantService.saveTenant(sysTenant));
    }


}

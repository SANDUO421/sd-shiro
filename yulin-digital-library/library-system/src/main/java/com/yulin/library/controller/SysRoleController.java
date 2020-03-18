package com.yulin.library.controller;

import com.google.common.base.Splitter;
import com.yulin.library.entity.SysRole;
import com.yulin.library.service.SysRoleService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @PreAuthorize(value="hasAuthority('sysRole:view')")
    @GetMapping("/sysRoles")
    public ApiResponse get(QueryCondition queryCondition){
        return ApiResponse.success(sysRoleService.listPage(queryCondition));
    }

    @PreAuthorize(value="hasAuthority('sysRole:create')")
    @PostMapping("/sysRole")
    public ApiResponse add(@RequestBody SysRole sysRole){
        return ApiResponse.success(sysRoleService.save(sysRole));
    }

    @PreAuthorize(value="hasAuthority('sysRole:update')")
    @PutMapping("/sysRole")
    public ApiResponse update(@RequestBody SysRole sysRole) throws Exception {
        return ApiResponse.success(sysRoleService.changeById(sysRole));
    }

    @PreAuthorize(value="hasAuthority('sysRole:delete')")
    @DeleteMapping("/sysRole/{id}")
    public ApiResponse delete(@PathVariable Long id){
        return ApiResponse.success(sysRoleService.removeById(id));
    }

    @PreAuthorize(value="hasAuthority('sysRole:delete')")
    @DeleteMapping("/sysRoles/{ids}")
    public ApiResponse deletes(@PathVariable String ids){
        return ApiResponse.success(sysRoleService.removeByIds(Splitter.on(",").trimResults().splitToList(ids)));
    }

}

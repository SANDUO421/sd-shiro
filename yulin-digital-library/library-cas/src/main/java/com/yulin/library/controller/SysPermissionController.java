package com.yulin.library.controller;

import com.google.common.base.Splitter;
import com.yulin.library.entity.SysPermission;
import com.yulin.library.service.SysPermissionService;
import com.yulin.library.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @PreAuthorize(value="hasAuthority('sysPermission:view')")
    @GetMapping("/sysPermissions")
    public ApiResponse get(){
        return ApiResponse.success(sysPermissionService.list());
    }

    @PreAuthorize(value="hasAuthority('sysPermission:create')")
    @PostMapping("/sysPermission")
    public ApiResponse add(@RequestBody SysPermission sysPermission){
        return ApiResponse.success(sysPermissionService.save(sysPermission));
    }

    @PreAuthorize(value="hasAuthority('sysPermission:update')")
    @PutMapping("/sysPermission")
    public ApiResponse update(@RequestBody SysPermission sysPermission){
        return ApiResponse.success(sysPermissionService.updateById(sysPermission));
    }

    @PreAuthorize(value="hasAuthority('sysPermission:delete')")
    @DeleteMapping("/sysPermission")
    public ApiResponse delete(@PathVariable Long id){
        return ApiResponse.success(sysPermissionService.removeById(id));
    }

    @PreAuthorize(value="hasAuthority('sysPermission:delete')")
    @DeleteMapping("/sysPermissions")
    public ApiResponse deletes(@PathVariable String ids){
        return ApiResponse.success(sysPermissionService.removeByIds(Splitter.on(",").trimResults().splitToList(ids)));
    }
}

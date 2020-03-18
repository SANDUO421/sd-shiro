package com.yulin.library.controller;

import com.google.common.base.Splitter;
import com.yulin.library.entity.SysMenu;
import com.yulin.library.service.SysMenuService;
import com.yulin.library.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @PreAuthorize(value="hasAuthority('sysMenu:view')")
    @GetMapping("/sysMenus")
    public ApiResponse get(){
        return ApiResponse.success(sysMenuService.list());
    }

    @PreAuthorize(value="hasAuthority('sysMenu:create')")
    @PostMapping("/sysMenu")
    public ApiResponse add(@RequestBody SysMenu sysMenu){
        return ApiResponse.success(sysMenuService.save(sysMenu));
    }

    @PreAuthorize(value="hasAuthority('sysMenu:update')")
    @PutMapping("/sysMenu")
    public ApiResponse update(@RequestBody SysMenu sysMenu){
        return ApiResponse.success(sysMenuService.updateById(sysMenu));
    }

    @PreAuthorize(value="hasAuthority('sysMenu:delete')")
    @DeleteMapping("/sysMenu")
    public ApiResponse delete(@PathVariable Long id){
        return ApiResponse.success(sysMenuService.removeById(id));
    }

    @PreAuthorize(value="hasAuthority('sysMenu:delete')")
    @DeleteMapping("/sysMenus")
    public ApiResponse deletes(@PathVariable String ids){
        return ApiResponse.success(sysMenuService.removeByIds(Splitter.on(",").trimResults().splitToList(ids)));
    }
}

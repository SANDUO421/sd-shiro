package com.yulin.library.controller;

import com.yulin.library.entity.SysUser;
import com.yulin.library.service.SysUserService;
import com.yulin.library.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

//    @PreAuthorize(value="hasAuthority('sysUser:view')")
    @GetMapping("/sysUsers")
    public ApiResponse get(){
        SysUser sysUser=new SysUser();
        sysUser.setUserName("username");
        sysUserService.save(sysUser);
        return ApiResponse.success(sysUserService.list());
    }

    @PreAuthorize(value="hasAuthority('sysUser:create')")
    @PostMapping("/sysUser")
    public ApiResponse add(@RequestBody SysUser sysUser){
        return ApiResponse.success(sysUserService.save(sysUser));
    }

    @PreAuthorize(value="hasAuthority('sysUser:update')")
    @PutMapping("/sysUser")
    public ApiResponse update(@RequestBody SysUser sysUser){
        return ApiResponse.success(sysUserService.updateById(sysUser));
    }

    @PreAuthorize(value="hasAuthority('sysUser:delete')")
    @DeleteMapping("/sysUser")
    public ApiResponse delete(@PathVariable Long id){
        return ApiResponse.success(sysUserService.removeById(id));
    }

}

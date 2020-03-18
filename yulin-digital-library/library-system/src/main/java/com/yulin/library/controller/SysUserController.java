package com.yulin.library.controller;

import com.yulin.library.entity.SysUser;
import com.yulin.library.service.SysUserService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

//    @PreAuthorize(value = "permitAll")
//    @PreAuthorize(value="hasAuthority('sysUser:view')")
    @GetMapping("/loginSelect")
    public SysUser loginSelect(String username){
        return sysUserService.loginSelect(username);
    }

//    @PreAuthorize(value="hasAuthority('sysUser:view')")
    @GetMapping("/sysUsers")
    public ApiResponse get(QueryCondition queryCondition){
        return ApiResponse.success(sysUserService.listPage(queryCondition));
    }

    @PreAuthorize(value="hasAuthority('sysUser:create')")
    @PostMapping("/sysUser")
    public ApiResponse add(@RequestBody SysUser sysUser){
        return ApiResponse.success(sysUserService.save(sysUser));
    }

    @PreAuthorize(value="hasAuthority('sysUser:update')")
    @PutMapping("/sysUser")
    public ApiResponse update(@RequestBody SysUser sysUser) throws Exception {
        return ApiResponse.success(sysUserService.changeById(sysUser));
    }

    @PreAuthorize(value="hasAuthority('sysUser:delete')")
    @DeleteMapping("/sysUser/{id}")
    public ApiResponse delete(@PathVariable Long id){
        return ApiResponse.success(sysUserService.removeById(id));
    }

}

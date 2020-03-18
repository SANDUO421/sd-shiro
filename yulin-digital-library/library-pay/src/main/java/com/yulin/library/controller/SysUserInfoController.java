package com.yulin.library.controller;

import com.google.common.base.Splitter;
import com.yulin.library.entity.SysUserInfo;
import com.yulin.library.service.SysUserInfoService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

@RestController
public class SysUserInfoController extends AbstractController<SysUserInfoService> {
    @GetMapping("/sysUserInfos")
    public ApiResponse get(QueryCondition queryCondition){
        return ApiResponse.success(service.listPage(queryCondition));
    }

    @GetMapping("/sysUserInfo/{id}")
    public ApiResponse getOne(@PathVariable("id") String id){
        return ApiResponse.success(service.selectByUserId(id));
    }

    @PostMapping("/sysUserInfo")
    public ApiResponse add(@RequestBody SysUserInfo sysUserInfo){
        return ApiResponse.success(service.save(sysUserInfo));
    }

    @PutMapping("/sysUserInfo")
    public ApiResponse update(@RequestBody SysUserInfo sysUserInfo) throws Exception {
        return ApiResponse.success(service.changeById(sysUserInfo));
    }

    @DeleteMapping("/sysMenu")
    public ApiResponse delete(@PathVariable Long id){
        return ApiResponse.success(service.removeById(id));
    }

    @DeleteMapping("/sysMenus")
    public ApiResponse deletes(@PathVariable String ids){
        return ApiResponse.success(service.removeByIds(Splitter.on(",").trimResults().splitToList(ids)));
    }
}

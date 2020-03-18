package com.yulin.library.controller;

import com.yulin.library.model.entity.VipAuthority;
import com.yulin.library.service.VipAuthorityService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VipAuthorityController extends AbstractController<VipAuthorityService> {

    @GetMapping("/vipAuthorities")
    public ApiResponse list(
            QueryCondition queryCondition){
        return ApiResponse.success(service.listPage(queryCondition));
    }

    @GetMapping("/vipAuthority/{id}")
    public ApiResponse get(@PathVariable String id){
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/vipAuthority")
    public ApiResponse add(@RequestBody VipAuthority vipAuthority) throws Exception {
        return ApiResponse.success(service.add(vipAuthority));
    }

    @PutMapping("/vipAuthority")
    public ApiResponse update(@RequestBody VipAuthority vipAuthority) throws Exception {
        return ApiResponse.success(service.updateById(vipAuthority));
    }

    @DeleteMapping("/vipAuthority/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/vipAuthorities/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }
}

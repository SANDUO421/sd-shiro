package com.yulin.library.controller;

import com.yulin.library.model.entity.AppIcon;
import com.yulin.library.service.AppIconService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppIconController extends AbstractController<AppIconService> {

    @GetMapping("/appIcons")
    public ApiResponse list(
            QueryCondition queryCondition,
            @RequestParam(required = false) String position){
        return ApiResponse.success(service.listPage(queryCondition,position));
    }

    @GetMapping("/appIcon/{id}")
    public ApiResponse get(@PathVariable String id){
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/appIcon")
    public ApiResponse add(@RequestBody AppIcon appIcon) throws Exception {
        return ApiResponse.success(service.add(appIcon));
    }

    @PutMapping("/appIcon")
    public ApiResponse update(@RequestBody AppIcon appIcon) throws Exception {
        return ApiResponse.success(service.updateById(appIcon));
    }

    @DeleteMapping("/appIcon/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/appIcons/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }
}

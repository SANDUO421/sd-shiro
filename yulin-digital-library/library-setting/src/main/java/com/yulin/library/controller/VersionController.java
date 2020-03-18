package com.yulin.library.controller;

import com.yulin.library.model.entity.Version;
import com.yulin.library.service.VersionService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VersionController extends AbstractController<VersionService> {

    @GetMapping("/versions")
    public ApiResponse list(
            QueryCondition queryCondition,
            @RequestParam(required = false) String type){
        return ApiResponse.success(service.listPage(queryCondition,type));
    }

    @GetMapping("/versionNewest")
    public ApiResponse getNewest(@RequestParam(required = false) String type){
        return ApiResponse.success(service.getNewest(type));
    }

    @GetMapping("/version/{id}")
    public ApiResponse get(@PathVariable String id){
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/version")
    public ApiResponse add(@RequestBody Version version) throws Exception {
        return ApiResponse.success(service.add(version));
    }

    @PutMapping("/version")
    public ApiResponse update(@RequestBody Version version) throws Exception {
        return ApiResponse.success(service.updateById(version));
    }

    @DeleteMapping("/version/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/versions/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }

}

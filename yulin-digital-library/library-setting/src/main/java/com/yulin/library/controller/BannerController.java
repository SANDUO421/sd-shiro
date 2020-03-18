package com.yulin.library.controller;

import com.yulin.library.model.entity.Banner;
import com.yulin.library.service.BannerService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BannerController extends AbstractController<BannerService> {

    @GetMapping("/banners")
    public ApiResponse list(
            QueryCondition queryCondition,
            @RequestParam(required = false) String position){
        return ApiResponse.success(service.listPage(queryCondition,position));
    }

    @GetMapping("/banner/{id}")
    public ApiResponse get(@PathVariable String id){
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/banner")
    public ApiResponse add(@RequestBody Banner banner) throws Exception {
        return ApiResponse.success(service.add(banner));
    }

    @PutMapping("/banner")
    public ApiResponse update(@RequestBody Banner banner) throws Exception {
        return ApiResponse.success(service.updateById(banner));
    }

    @DeleteMapping("/banner/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/banners/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }
}

package com.yulin.library.controller;

import com.yulin.library.model.entity.VipLever;
import com.yulin.library.service.VipLeverService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VipLeverController extends AbstractController<VipLeverService> {

    @GetMapping("/vipLevers")
    public ApiResponse list(
            QueryCondition queryCondition){
        return ApiResponse.success(service.listPage(queryCondition));
    }

    @GetMapping("/vipLever/{id}")
    public ApiResponse get(@PathVariable String id){
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/vipLever")
    public ApiResponse add(@RequestBody VipLever vipLever) throws Exception {
        return ApiResponse.success(service.add(vipLever));
    }

    @PutMapping("/vipLever")
    public ApiResponse update(@RequestBody VipLever vipLever) throws Exception {
        return ApiResponse.success(service.updateById(vipLever));
    }

    @DeleteMapping("/vipLever/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/vipLevers/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }
}

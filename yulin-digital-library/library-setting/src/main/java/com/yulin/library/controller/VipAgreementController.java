package com.yulin.library.controller;

import com.yulin.library.model.entity.VipAgreement;
import com.yulin.library.service.VipAgreementService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VipAgreementController extends AbstractController<VipAgreementService> {

    @GetMapping("/vipAgreements")
    public ApiResponse list(
            QueryCondition queryCondition){
        return ApiResponse.success(service.listPage(queryCondition));
    }

    @GetMapping("/vipAgreement/{id}")
    public ApiResponse get(@PathVariable String id){
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/vipAgreement")
    public ApiResponse add(@RequestBody VipAgreement vipAgreement) throws Exception {
        return ApiResponse.success(service.add(vipAgreement));
    }

    @PutMapping("/vipAgreement")
    public ApiResponse update(@RequestBody VipAgreement vipAgreement) throws Exception {
        return ApiResponse.success(service.updateById(vipAgreement));
    }

    @DeleteMapping("/vipAgreement/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/vipAgreements/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }
}

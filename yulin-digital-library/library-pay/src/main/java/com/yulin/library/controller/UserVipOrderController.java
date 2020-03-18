package com.yulin.library.controller;

import com.google.common.base.Splitter;
import com.yulin.library.entity.UserVipOrder;
import com.yulin.library.service.UserVipOrderService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserVipOrderController extends AbstractUserOrderController<UserVipOrderService> {
    @GetMapping("/userVipOrders")
    public ApiResponse get(QueryCondition queryCondition){
        return ApiResponse.success(service.listPage(queryCondition));
    }

    @PostMapping("/userVipOrder")
    public ApiResponse add(@RequestBody UserVipOrder userVipOrder){
        return ApiResponse.success(service.createOrder(userVipOrder));
    }

    @DeleteMapping("/userVipOrder/{id}")
    public ApiResponse delete(@PathVariable Long id){
        return ApiResponse.success(service.removeById(id));
    }

    @DeleteMapping("/userVipOrders/{ids}")
    public ApiResponse deletes(@PathVariable String ids){
        return ApiResponse.success(service.removeByIds(Splitter.on(",").trimResults().splitToList(ids)));
    }
}

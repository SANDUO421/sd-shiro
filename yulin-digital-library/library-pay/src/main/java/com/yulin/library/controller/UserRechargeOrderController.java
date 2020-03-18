package com.yulin.library.controller;

import com.google.common.base.Splitter;
import com.yulin.library.entity.UserRechargeOrder;
import com.yulin.library.service.UserRechargeOrderService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRechargeOrderController extends AbstractUserOrderController<UserRechargeOrderService> {
    @GetMapping("/userRechargeOrders")
    public ApiResponse get(QueryCondition queryCondition){
        return ApiResponse.success(service.listPage(queryCondition));
    }

    @PostMapping("/userRechargeOrder")
    public ApiResponse add(@RequestBody UserRechargeOrder userRechargeOrder){
        return ApiResponse.success(service.createOrder(userRechargeOrder));
    }

    @DeleteMapping("/userRechargeOrder/{id}")
    public ApiResponse delete(@PathVariable Long id){
        return ApiResponse.success(service.removeById(id));
    }

    @DeleteMapping("/userRechargeOrders/{ids}")
    public ApiResponse deletes(@PathVariable String ids){
        return ApiResponse.success(service.removeByIds(Splitter.on(",").trimResults().splitToList(ids)));
    }
}

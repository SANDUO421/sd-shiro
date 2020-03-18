package com.yulin.library.controller;

import com.yulin.library.model.entity.ReadingHistory;
import com.yulin.library.service.ReadingHistoryService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReadingHistoryController extends AbstractController<ReadingHistoryService> {

    @GetMapping("/readingHistories")
    public ApiResponse list(
            QueryCondition queryCondition,
            @RequestParam(required = false) String name){
        return ApiResponse.success(service.listPage(queryCondition,name));
    }

    @GetMapping("/readingHistory/{id}")
    public ApiResponse get(@PathVariable String id){
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/readingHistory")
    public ApiResponse add(@RequestBody ReadingHistory readingHistory) throws Exception {
        return ApiResponse.success(service.add(readingHistory));
    }

    @PutMapping("/readingHistory")
    public ApiResponse update(@RequestBody ReadingHistory readingHistory) throws Exception {
        return ApiResponse.success(service.updateById(readingHistory));
    }

    @DeleteMapping("/readingHistory/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/readingHistories/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }
}

package com.yulin.library.controller;

import com.yulin.library.model.entity.News;
import com.yulin.library.service.NewsService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsController extends AbstractController<NewsService> {

    @GetMapping("/newses")
    public ApiResponse list(
            QueryCondition queryCondition,
            @RequestParam(required = false) String title){
        return ApiResponse.success(service.listPage(queryCondition,title));
    }

    @GetMapping("/news/{id}")
    public ApiResponse get(@PathVariable String id){
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/news")
    public ApiResponse add(@RequestBody News news) throws Exception {
        return ApiResponse.success(service.add(news));
    }

    @PutMapping("/news")
    public ApiResponse update(@RequestBody News news) throws Exception {
        return ApiResponse.success(service.updateById(news));
    }

    @DeleteMapping("/news/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/newses/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }

}

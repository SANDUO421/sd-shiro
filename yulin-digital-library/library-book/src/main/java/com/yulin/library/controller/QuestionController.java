package com.yulin.library.controller;

import com.yulin.library.model.entity.Question;
import com.yulin.library.service.QuestionService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController extends AbstractController<QuestionService> {

    @GetMapping("/questions")
    public ApiResponse list(
            QueryCondition queryCondition,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) Integer state) {
        return ApiResponse.success(service.listPage(queryCondition, userId,content,state));
    }

    @GetMapping("/question/{id}")
    public ApiResponse get(@PathVariable String id) {
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/question")
    public ApiResponse add(@RequestBody Question question) throws Exception {
        return ApiResponse.success(service.add(question));
    }

    @PutMapping("/question")
    public ApiResponse update(@RequestBody Question question) throws Exception {
        return ApiResponse.success(service.updateById(question));
    }

    @DeleteMapping("/question/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/questions/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }
}

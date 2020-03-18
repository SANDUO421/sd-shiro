package com.yulin.library.controller;

import com.yulin.library.model.entity.QuestionAnswer;
import com.yulin.library.service.QuestionAnswerService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionAnswerController extends AbstractController<QuestionAnswerService> {

    @GetMapping("/questionAnswers")
    public ApiResponse list(
            QueryCondition queryCondition,
            @RequestParam String questionId,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) Integer state) {
        return ApiResponse.success(service.listPage(queryCondition, questionId,content,state));
    }

    @GetMapping("/questionAnswer/{id}")
    public ApiResponse get(@PathVariable String id) {
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/questionAnswer")
    public ApiResponse add(@RequestBody QuestionAnswer questionAnswer) throws Exception {
        return ApiResponse.success(service.add(questionAnswer));
    }

    @PutMapping("/questionAnswer")
    public ApiResponse update(@RequestBody QuestionAnswer questionAnswer) throws Exception {
        return ApiResponse.success(service.updateById(questionAnswer));
    }

    @DeleteMapping("/questionAnswer/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/questionAnswers/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }
}

package com.yulin.library.controller;

import com.yulin.library.model.entity.UserComment;
import com.yulin.library.service.UserCommentService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserCommentController extends AbstractController<UserCommentService> {

    @GetMapping("/userComments")
    public ApiResponse list(
            QueryCondition queryCondition,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) Integer state,
            @RequestParam(required = false) String bookId) {
        return ApiResponse.success(service.listPage(queryCondition, content, state,bookId));
    }

    @GetMapping("/userComment/{id}")
    public ApiResponse get(@PathVariable String id) {
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/userComment")
    public ApiResponse add(@RequestBody UserComment userComment) throws Exception {
        return ApiResponse.success(service.add(userComment));
    }

    @PutMapping("/userComment")
    public ApiResponse update(@RequestBody UserComment userComment) throws Exception {
        return ApiResponse.success(service.updateById(userComment));
    }

    @DeleteMapping("/userComment/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/userComments/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }
}

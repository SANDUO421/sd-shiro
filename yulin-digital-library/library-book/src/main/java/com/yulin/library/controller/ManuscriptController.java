package com.yulin.library.controller;

import com.yulin.library.model.entity.Manuscript;
import com.yulin.library.model.entity.Question;
import com.yulin.library.service.ManuscriptService;
import com.yulin.library.service.QuestionService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManuscriptController extends AbstractController<ManuscriptService> {

    @GetMapping("/manuscripts")
    public ApiResponse list(
            QueryCondition queryCondition,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer userState,
            @RequestParam(required = false) Integer state) {
        return ApiResponse.success(service.listPage(queryCondition, title,userState,state));
    }

    @GetMapping("/manuscript/{id}")
    public ApiResponse get(@PathVariable String id) {
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/manuscript")
    public ApiResponse add(@RequestBody Manuscript manuscript) throws Exception {
        return ApiResponse.success(service.add(manuscript));
    }

    @PutMapping("/manuscript")
    public ApiResponse update(@RequestBody Manuscript manuscript) throws Exception {
        return ApiResponse.success(service.updateById(manuscript));
    }

    @DeleteMapping("/manuscript/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/manuscripts/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }
}

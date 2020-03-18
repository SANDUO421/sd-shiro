package com.yulin.library.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.yulin.library.model.entity.BookContent;
import com.yulin.library.service.BookContentService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookContentController extends AbstractController<BookContentService> {

//    @PreAuthorize(value="hasAuthority('bookContent:view')")
//    @PreAuthorize(value="isAuthenticated()")
    @JsonView(BookContent.SimpleView.class)
    @GetMapping("/bookContents")
    public ApiResponse list(
            QueryCondition queryCondition,
            Authentication authentication,
            @RequestParam(required = false) String bookId){
        return ApiResponse.success(service.listPage(queryCondition,bookId));
    }

//    @PreAuthorize(value="hasAuthority('bookContent:view')")
    @JsonView(BookContent.ContentView.class)
    @GetMapping("/bookContent/{id}")
    public ApiResponse get(@PathVariable String id){
        return ApiResponse.success(service.findById(id));
    }

//    @PreAuthorize(value="hasAuthority('bookContent:add')")
    @PostMapping("/bookContent")
    public ApiResponse add(@RequestBody BookContent bookContent) throws Exception {
        return ApiResponse.success(service.add(bookContent));
    }

//    @PreAuthorize(value="hasAuthority('bookContent:update')")
    @PutMapping("/bookContent")
    public ApiResponse update(@RequestBody BookContent bookContent) throws Exception {
        return ApiResponse.success(service.updateById(bookContent));
    }

//    @PreAuthorize(value="hasAuthority('bookContent:delete')")
    @DeleteMapping("/bookContent/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

//    @PreAuthorize(value="hasAuthority('bookContent:delete')")
    @DeleteMapping("/bookContents/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }

}

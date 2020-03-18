package com.yulin.library.controller;

import com.yulin.library.model.entity.Book;
import com.yulin.library.service.BookService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController extends AbstractController<BookService> {

    @GetMapping("/books")
    public ApiResponse list(
            QueryCondition queryCondition,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> categories,
            @RequestParam(required = false) Integer isFree,
            @RequestParam(required = false) Integer isRecommend,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) Integer isVip,
            @RequestParam(required = false) Integer isFamous){
        return ApiResponse.success(service.listPage(queryCondition,name,categories,isFree,isRecommend,type,isVip,isFamous));
    }

    @GetMapping("/book/{id}")
    public ApiResponse<Book> get(@PathVariable String id){
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/book")
    public ApiResponse<Book> add(@RequestBody Book book) throws Exception {
        return ApiResponse.success(service.add(book));
    }

    @PutMapping("/book")
    public ApiResponse update(@RequestBody Book book) throws Exception {
        return ApiResponse.success(service.updateById(book));
    }

    @DeleteMapping("/book/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/books/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }
}

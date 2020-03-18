package com.yulin.library.controller;

import com.yulin.library.model.entity.Bookshelf;
import com.yulin.library.service.BookshelfService;
import com.yulin.library.util.ApiResponse;
import com.yulin.library.util.model.page.QueryCondition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookshelfController extends AbstractController<BookshelfService> {

    @GetMapping("/bookshelves")
    public ApiResponse list(
            QueryCondition queryCondition,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String name){
        return ApiResponse.success(service.listPage(queryCondition,userId,name));
    }

    @GetMapping("/bookshelf/{id}")
    public ApiResponse get(@PathVariable String id){
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/bookshelf")
    public ApiResponse add(@RequestBody Bookshelf bookshelf) throws Exception {
        return ApiResponse.success(service.add(bookshelf));
    }

    @PutMapping("/bookshelf")
    public ApiResponse update(@RequestBody Bookshelf bookshelf) throws Exception {
        return ApiResponse.success(service.updateById(bookshelf));
    }

    @DeleteMapping("/bookshelf/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/bookshelves/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }
}

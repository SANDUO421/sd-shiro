package com.yulin.library.controller;

import com.yulin.library.model.entity.DataDictionary;
import com.yulin.library.service.DataDictionaryService;
import com.yulin.library.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataDictionaryController extends AbstractController<DataDictionaryService> {

    @GetMapping("/dataDictionarys")
    public ApiResponse list(@RequestParam(required = false) String type){
        return ApiResponse.success(service.list(null,type));
    }

    @GetMapping("/dataDictionary/{id}")
    public ApiResponse get(@PathVariable String id){
        return ApiResponse.success(service.findById(id));
    }

    @PostMapping("/dataDictionary")
    public ApiResponse add(@RequestBody DataDictionary dataDictionary) throws Exception {
        return ApiResponse.success(service.add(dataDictionary));
    }

    @PutMapping("/dataDictionary")
    public ApiResponse update(@RequestBody DataDictionary dataDictionary) throws Exception {
        return ApiResponse.success(service.updateById(dataDictionary));
    }

    @DeleteMapping("/dataDictionary/{id}")
    public ApiResponse delete(@PathVariable String id) throws Exception {
        return ApiResponse.success(service.deleteById(id));
    }

    @DeleteMapping("/dataDictionarys/{ids}")
    public ApiResponse deleteMany(@PathVariable List<String> ids) throws Exception {
        return ApiResponse.success(service.deleteByIds(ids));
    }

}

package com.yulin.library.controller;

import com.yulin.library.service.CommonService;
import com.yulin.library.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class CommonController extends AbstractController<CommonService> {

    @Autowired
    private CommonService commonService;

    @PostMapping("/files")
    public ApiResponse fileUpload(@RequestParam(value = "files")MultipartFile[] files) throws IOException {
        return ApiResponse.success(commonService.fileUpload(files));
    }

    @PostMapping("/files/cos")
    public ApiResponse fileUploadCos(@RequestParam(value = "files")MultipartFile[] files) throws IOException {
        return ApiResponse.success(commonService.fileUpload(files));
    }

}

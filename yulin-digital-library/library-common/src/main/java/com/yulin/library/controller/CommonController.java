package com.yulin.library.controller;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import com.yulin.library.model.entity.PushData;
import com.yulin.library.service.CommonService;
import com.yulin.library.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        return ApiResponse.success(commonService.fileUpload(files,null));
    }

    @PostMapping("/files/cos")
    public ApiResponse fileUploadCos(@RequestParam(value = "files")MultipartFile[] files) throws IOException {
        return ApiResponse.success(commonService.fileUploadCos(files));
    }

    @PostMapping("/files/version")
    public ApiResponse fileUploadVersion(
            @RequestParam(value = "files")MultipartFile[] files,
            @RequestParam(value = "fileName",required = false,defaultValue = "app") String fileName
    ) throws IOException {
        return ApiResponse.success(commonService.fileUpload(files,fileName));
    }

    @PostMapping("/push")
    public ApiResponse push(@RequestBody PushData pushData) throws APIConnectionException, APIRequestException {
        return ApiResponse.success(commonService.push(pushData));
    }

}

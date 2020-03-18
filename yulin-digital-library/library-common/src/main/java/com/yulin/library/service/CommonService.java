package com.yulin.library.service;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import com.yulin.library.model.entity.PushData;
import com.yulin.library.mongodb.model.entity.BaseEntity;
import com.yulin.library.mongodb.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CommonService extends BaseService<BaseEntity> {
    String[] fileUpload(MultipartFile[] files,String fileName) throws IOException;

    String[] fileUploadCos(MultipartFile[] files) throws IOException;

    PushResult push(PushData pushData) throws APIConnectionException, APIRequestException;
}

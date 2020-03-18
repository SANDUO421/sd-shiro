package com.yulin.library.service;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import com.yulin.library.mongodb.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CommonService extends BaseService<BaseEntity> {
    String[] fileUpload(MultipartFile[] files) throws IOException;
}

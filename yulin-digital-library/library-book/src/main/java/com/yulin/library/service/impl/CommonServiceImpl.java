package com.yulin.library.service.impl;

import cn.hutool.core.io.FileUtil;
import com.yulin.library.mongodb.model.entity.BaseEntity;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.CommonRepository;
import com.yulin.library.service.CommonService;
import com.yulin.library.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class CommonServiceImpl extends AbstractService<CommonRepository,BaseEntity> implements CommonService {
    @Value("${file.upload.path}")
    private String fileUploadPath;
    @Override
    public String[] fileUpload(MultipartFile[] files) throws IOException {
        String[] result=new String[files.length];
        String path=FileUtils.getPath();
        String uploadPath=fileUploadPath+path;
        FileUtil.mkdir(uploadPath);
        for (int i = 0; i < files.length; i++) {
            String extName = FileUtil.extName(files[i].getOriginalFilename());
            String fileName=FileUtils.getRandomFileName(extName);
            result[i]=path+fileName;
            fileName=uploadPath+fileName;
            File file=new File(fileName);
            files[i].transferTo(file);
        }
        return result;
    }

    @Override
    protected Class getEntityClass() {
        return BaseEntity.class;
    }
}

package com.yulin.library.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.yulin.library.model.entity.FileData;
import com.yulin.library.model.entity.PushData;
import com.yulin.library.mongodb.model.entity.BaseEntity;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.CommonRepository;
import com.yulin.library.repository.FileDataRepository;
import com.yulin.library.repository.PushDataRepository;
import com.yulin.library.service.CommonService;
import com.yulin.library.util.Constants;
import com.yulin.library.util.FileUtils;
import com.yulin.library.util.MyBeanUtils;
import com.yulin.library.util.propertie.tencent.TencentProperties;
import com.yulin.library.util.push.PushUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommonServiceImpl extends AbstractService<CommonRepository,BaseEntity> implements CommonService {
    @Value("${file.upload.path}")
    private String fileUploadPath;

    @Autowired
    private COSClient cosClient;

    @Autowired
    private TencentProperties tencent;

    @Autowired
    private FileDataRepository fileDataRepository;

    @Autowired
    private PushDataRepository pushDataRepository;

    @Autowired
    private PushUtils pushUtils;

    @Override
    public String[] fileUpload(MultipartFile[] files,String fileName) throws IOException {
        String[] result=new String[files.length];
        List<FileData> fileDatas=new ArrayList<>(files.length);
        String path=FileUtils.getPath();
        String uploadPath=fileUploadPath+path;
        FileUtil.mkdir(uploadPath);
        for (int i = 0; i < files.length; i++) {
            String extName = FileUtil.extName(files[i].getOriginalFilename());
            if(StringUtils.isBlank(fileName)){
                fileName=FileUtils.getRandomFileName(extName);
                result[i]=path+fileName;
                fileName=uploadPath+fileName;
            }else{
                File descFile=new File(fileUploadPath+File.separator+fileName+ DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN));
                result[i]=fileUploadPath+File.separator+fileName;
                File srcFile=new File(result[i]);
                if(srcFile.exists()){
                    FileUtil.copy(srcFile,descFile,true);
                }
                fileName=result[i];
            }
            File file=new File(fileName);
            files[i].transferTo(file);

            FileData fileData=new FileData();
            fileData.setOriginalFilename(files[i].getOriginalFilename());
            fileData.setFileName(fileName);
            fileData.setFilePath(uploadPath);
            fileData.setType("0");
            fileDatas.add(fileData);
        }
        fileDataRepository.saveAll(fileDatas);
        return result;
    }

    @Override
    public String[] fileUploadCos(MultipartFile[] files) throws IOException {
        String[] result=new String[files.length];
        List<FileData> fileDatas=new ArrayList<>(files.length);
        for (int i = 0; i < files.length; i++) {
            String extName = FileUtil.extName(files[i].getOriginalFilename());
            String fileName=FileUtils.getRandomFileName(extName);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            // 设置输入流长度为500
            objectMetadata.setContentLength(files[i].getSize());
            // 设置 Content type, 默认是 application/octet-stream
            objectMetadata.setContentType(files[i].getContentType());
            cosClient.putObject(tencent.getCos().getBucketName(),fileName,files[i].getInputStream(),objectMetadata);
            result[i]=fileName;

            FileData fileData=new FileData();
            fileData.setOriginalFilename(files[i].getOriginalFilename());
            fileData.setFileName(fileName);
            fileData.setFilePath("");
            fileData.setType("0");
            fileDatas.add(fileData);
        }
        fileDataRepository.saveAll(fileDatas);
        return result;
    }

    @Override
    public PushResult push(PushData pushData) throws APIConnectionException, APIRequestException {
        if(StringUtils.isBlank(pushData.getAudienceType())){
            pushData.setAudienceType(Constants.PushAudience.ALL);
        }
        if(StringUtils.isBlank(pushData.getPlatform())){
            pushData.setPlatform(Constants.PushPlatform.ALL);
        }
        com.yulin.library.util.push.entity.PushData sendPushData=new com.yulin.library.util.push.entity.PushData();
        MyBeanUtils.copyProperties(pushData,sendPushData);
        PushResult pushResult = pushUtils.send(sendPushData);
        pushData.setId(String.valueOf(pushResult.msg_id));
        pushDataRepository.save(pushData);
        return pushResult;
    }

    @Override
    protected Class getEntityClass() {
        return BaseEntity.class;
    }
}

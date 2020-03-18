package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class FileData extends BaseEntity {
    private static final long serialVersionUID = 2849935740556045532L;

    /**
     * 原文件名
     */
    private String originalFilename;
    /**
     * 存储文件名
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件类型（0-本地文件，1-腾讯云）
     */
    private String type;


}

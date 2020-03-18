package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;

/**
 * @author lingyu
 */
@Data
public class Version extends BaseEntity {
    private static final long serialVersionUID = 4327868471282736629L;

    /**
     * 版本名称
     */
    private String name;
    /**
     * android,ios
     */
    private String type;
    /**
     * 版本号
     */
    private String versionCode;
    /**
     * 版本顺序号
     */
    private Long code;
    /**
     * 下载路径
     */
    private String path;
    /**
     * 描述
     */
    private String description;

}

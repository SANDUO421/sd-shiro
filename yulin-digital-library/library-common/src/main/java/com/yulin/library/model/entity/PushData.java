package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Document
public class PushData extends BaseEntity {
    /**
     * 内容
     */
    private String alert;
    /**
     * 标题
     */
    private String title;
    /**
     * 平台（all，android，ios）
     */
    private String platform;
    /**
     * 发送类型（all，tag，alias，registration_id）
     */
    private String audienceType;
    /**
     * 发送别名
     */
    private List<String> alias;
    /**
     * 发送组
     */
    private List<String> tags;
    /**
     * 发送注册id
     */
    private List<String> registrationIds;
    /**
     * 跳转连接
     */
    private String uriActivity;
    /**
     * 跳转连接
     */
    private String uriAction;
    /**
     * 角标
     */
    private Integer badgeAddNum=1;
    /**
     * 扩展内容
     */
    private Map<String, String> extras = new HashMap<>();
    /**
     * 自定义参数
     */
    private Map<String, String> custom = new HashMap<>();
}

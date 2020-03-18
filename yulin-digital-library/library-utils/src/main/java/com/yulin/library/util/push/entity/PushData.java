package com.yulin.library.util.push.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class PushData implements java.io.Serializable {
    private static final long serialVersionUID = -2597967769703351843L;

    private String alert;
    private String title;
    private String platform;
    private String audienceType;
    private List<String> alias;
    private List<String> tags;
    private List<String> registrationIds;
    private String uriActivity;
    private String uriAction;
    private Integer badgeAddNum=1;
    private Map<String, String> extras = new HashMap<>();
    private Map<String, String> custom = new HashMap<>();

}
package com.module.log.app.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 三多
 * @Time 2020/3/2
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog implements Serializable {

    /**主键*/
    @TableId(value="id",type = IdType.AUTO)
    private Integer id;

    /**操作人的Ip*/
    private String ip;
    /**操作地点*/
    private String location;

    /**操作类型 1 操作记录 2 异常记录*/
    private Integer type;

    /**操作人名称/Id*/
    private String username;
    /**操作描述*/
    private String  description;
    /**请求方法*/
    private String actionMethod;
    /**请求URL*/
    private String actionUrl;
    /**请求参数*/
    private String params;
    /**操作系统*/
    private String os;
    /**浏览器*/
    private String browser;
    /**类路径*/
    private String classPath;
    /**请求方法*/
    private String  requestMethod;
    /**开始时间*/
    @JsonFormat(shape =JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /**结束时间*/
    @JsonFormat(shape =JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishTime;
    /**耗时*/
    private long consumingTime;
    /**异常详情信息 堆栈信息*/
    private String exDetail;
    /**异常描述 e.getMessage*/
    private String exDesc;




}

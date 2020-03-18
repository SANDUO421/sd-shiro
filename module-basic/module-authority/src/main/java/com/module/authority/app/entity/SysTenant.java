package com.module.authority.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.module.authority.app.utils.Create;
import com.module.authority.app.utils.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * @description:    租户表
 * @author:         sanduo
 * @date:           2020/3/2 14:35
 * @version:        1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_tenant")
public class SysTenant implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 租户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "id 不能为空")
    @Length(groups = {Update.class})
    private Integer id;

    /**
     * 租户名称
     */
    @NotNull(message = "name 不能为空")
    @Length(groups = {Create.class})
    private String name;

    /**
     * 租户编号
     */
    private String code;

    /**
     * 授权开始时间
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @Future(message = "时间必须是将来时间", groups = {Create.class})
    private LocalDateTime startTime;

    /**
     * 授权结束时间
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 0正常 9-冻结
     */
    private int status;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 创建时间
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @Future(message = "时间必须是将来时间", groups = {Create.class})
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @Future(message = "时间必须是将来时间", groups = {Create.class})
    private LocalDateTime updateTime;


}

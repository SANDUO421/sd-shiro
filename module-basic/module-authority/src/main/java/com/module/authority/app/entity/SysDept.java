package com.module.authority.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门管理
 *
 * @author 三多
 * @Time 2020/3/4
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dept")
public class SysDept extends Model<SysDept> {

    /**
     * 部门主键ID
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;

    /**
     * 部门名称
     */
    private String name;
    /**
     * 上级部门
     */
    private Integer parentId;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    private String delFlag;

    /**
     * 非数据库字段
     * 上级部门
     */
    @TableField(exist = false)
    private String parentName;
    /**
     * 非数据库字段
     * 等级
     */
    @TableField(exist = false)
    private Integer level;

    /**
     * 非数据库字段
     * 子部门
     */
    @TableField(exist = false)
    private List<SysDept> children;
}

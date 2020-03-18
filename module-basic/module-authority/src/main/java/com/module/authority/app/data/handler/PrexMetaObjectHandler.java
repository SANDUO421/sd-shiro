package com.module.authority.app.data.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 字段自动填充器
 * @author 三多
 * @Time 2020/3/5
 */
@Slf4j
@Component
public class PrexMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ...");
        //避免使用metaObject.setValue()
        this.setFieldValByName("delFlag","0",metaObject);
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("operator", "SanDuo", metaObject);
    }
}

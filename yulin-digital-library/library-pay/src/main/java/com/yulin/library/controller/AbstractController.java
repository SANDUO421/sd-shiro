package com.yulin.library.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController<T extends IService> {

    @Autowired
    protected T service;

}

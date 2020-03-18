package com.yulin.library.controller;

import com.yulin.library.mongodb.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController<T extends BaseService> {

    @Autowired
    protected T service;

}

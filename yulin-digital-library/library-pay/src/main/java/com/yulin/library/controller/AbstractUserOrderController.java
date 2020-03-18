package com.yulin.library.controller;

import com.yulin.library.service.BaseUserOrderService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractUserOrderController<T extends BaseUserOrderService> extends AbstractController<T> {
    @Autowired
    protected T service;
}

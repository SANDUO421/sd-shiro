package com.yulin.library.service;

import com.yulin.library.model.entity.Version;
import com.yulin.library.model.entity.VipLever;
import com.yulin.library.mongodb.service.BaseService;

public interface VersionService extends BaseService<Version> {

    Version getNewest(String type);

}

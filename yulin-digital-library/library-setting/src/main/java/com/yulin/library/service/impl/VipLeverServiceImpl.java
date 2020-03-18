package com.yulin.library.service.impl;

import com.yulin.library.model.entity.VipAuthority;
import com.yulin.library.model.entity.VipLever;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.VipAuthorityRepository;
import com.yulin.library.repository.VipLeverRepository;
import com.yulin.library.service.VipAuthorityService;
import com.yulin.library.service.VipLeverService;
import org.springframework.stereotype.Service;

@Service
public class VipLeverServiceImpl extends AbstractService<VipLeverRepository, VipLever> implements VipLeverService {

    @Override
    protected Class<VipLever> getEntityClass() {
        return VipLever.class;
    }

}

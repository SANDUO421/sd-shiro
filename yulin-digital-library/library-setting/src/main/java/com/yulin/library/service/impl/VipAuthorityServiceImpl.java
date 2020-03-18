package com.yulin.library.service.impl;

import com.yulin.library.model.entity.VipAuthority;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.VipAuthorityRepository;
import com.yulin.library.service.VipAuthorityService;
import org.springframework.stereotype.Service;

@Service
public class VipAuthorityServiceImpl extends AbstractService<VipAuthorityRepository, VipAuthority> implements VipAuthorityService {

    @Override
    protected Class<VipAuthority> getEntityClass() {
        return VipAuthority.class;
    }

}

package com.yulin.library.service.impl;

import com.yulin.library.model.entity.VipAgreement;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.VipAgreementRepository;
import com.yulin.library.service.VipAgreementService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VipAgreementServiceImpl extends AbstractService<VipAgreementRepository, VipAgreement> implements VipAgreementService {

    @Override
    protected Class<VipAgreement> getEntityClass() {
        return VipAgreement.class;
    }

}

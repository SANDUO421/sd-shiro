package com.yulin.library.service.impl;

import com.yulin.library.model.entity.Manuscript;
import com.yulin.library.model.entity.Question;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.ManuscriptRepository;
import com.yulin.library.repository.QuestionRepository;
import com.yulin.library.service.ManuscriptService;
import com.yulin.library.service.QuestionService;
import com.yulin.library.util.Constants;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class ManuscriptServiceImpl extends AbstractService<ManuscriptRepository, Manuscript> implements ManuscriptService {

    @Override
    protected void beforeUpdate(Manuscript entity,Manuscript oldEntity) throws Exception {
        Date now=new Date();
        // 如果是审核通过
        if(Constants.AuditState.INSANITY.equals(entity.getState())){
            entity.setPassTime(now);
        }
        // 如果是审核不通过
        if(Constants.AuditState.AUDIT_FAILS.equals(entity.getState())){
            entity.setRejectTime(now);
        }
    }

    @Override
    protected void addListCondition(Criteria criteria, Object[] data) {
        if(Objects.nonNull(data[0])){
            criteria.and("title").regex(data[0].toString());
        }
        if(Objects.nonNull(data[1])){
            criteria.and("userState").is(data[1]);
        }
        if(Objects.nonNull(data[2])){
            criteria.and("state").is(data[2]);
        }
    }

    @Override
    protected Class<Manuscript> getEntityClass() {
        return Manuscript.class;
    }

}

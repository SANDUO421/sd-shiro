package com.yulin.library.service.impl;

import com.yulin.library.model.entity.Question;
import com.yulin.library.model.entity.UserComment;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.QuestionRepository;
import com.yulin.library.repository.UserCommentRepository;
import com.yulin.library.service.QuestionService;
import com.yulin.library.util.Constants;
import com.yulin.library.util.model.entity.CurrentUser;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class QuestionServiceImpl extends AbstractService<QuestionRepository, Question> implements QuestionService {

    @Override
    protected void beforeUpdate(Question entity,Question oldEntity) throws Exception {
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
            criteria.and("createUser.id").is(data[0]);
        }
        if(Objects.nonNull(data[1])){
            criteria.and("content").regex(data[1].toString());
        }
        if(Objects.nonNull(data[2])){
            criteria.and("state").is(data[2]);
        }
    }

    @Override
    protected Class<Question> getEntityClass() {
        return Question.class;
    }

}

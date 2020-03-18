package com.yulin.library.service.impl;

import com.yulin.library.model.entity.Question;
import com.yulin.library.model.entity.QuestionAnswer;
import com.yulin.library.mongodb.service.AbstractService;
import com.yulin.library.repository.QuestionAnswerRepository;
import com.yulin.library.service.QuestionAnswerService;
import com.yulin.library.service.QuestionService;
import com.yulin.library.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class QuestionAnswerServiceImpl extends AbstractService<QuestionAnswerRepository, QuestionAnswer> implements QuestionAnswerService {

    @Autowired
    private QuestionService questionService;

    @Override
    protected void beforeUpdate(QuestionAnswer entity,QuestionAnswer oldEntity) throws Exception {
        Date now=new Date();
        // 如果是审核通过
        if(Constants.AuditState.INSANITY.equals(entity.getState())){
            entity.setPassTime(now);
//            increaseQuestionAnsowerCount(oldEntity.getQuestion().getId());
            // 如果是审核不通过
        }else if(Constants.AuditState.AUDIT_FAILS.equals(entity.getState())){
            entity.setRejectTime(now);
        }
    }

    @Override
    protected void afterUpdate(QuestionAnswer entity) throws Exception {
        // 修改问题是否需要审核和回答个数
        changeQuestionIsverifyAndAnswerCount(entity);
    }

    private void changeQuestionIsverifyAndAnswerCount(QuestionAnswer entity) throws Exception {
        long waitconfirmCount = getCount(entity,Constants.AuditState.WAITCONFIRM);
        long answerCount=getCount(entity,Constants.AuditState.INSANITY);
        if(waitconfirmCount==0){
            changeQuestionIsverifyAndAnswerCount(entity,0,answerCount);
        }else{
            changeQuestionIsverifyAndAnswerCount(entity,null,answerCount);
        }
    }

    private long getCount(QuestionAnswer entity,Integer state) {
        Query query=new Query();
        Criteria criteria = Criteria.where("isDelete").is(Constants.NOT_DELETE)
                .and("state").is(state)
                .and("question").is(entity.getQuestion());
        query.addCriteria(criteria);
        return mongoTemplate.count(query, getEntityClass());
    }

    @Override
    protected void afterAdd(QuestionAnswer entity) throws Exception {
        if(Constants.AuditState.WAITCONFIRM.equals(entity.getState())){
            changeQuestionIsverifyAndAnswerCount(entity,1,null);
        }
    }

    private void changeQuestionIsverifyAndAnswerCount(QuestionAnswer entity,Integer state,Long answerCount) throws Exception {
        Question question = entity.getQuestion();
        if(Objects.nonNull(state)){
            entity.getQuestion().setIsverify(state);
        }
        if(Objects.nonNull(answerCount)){
            entity.getQuestion().setAnswerCount(answerCount);
        }
        questionService.updateById(question);
    }

//    private void increaseQuestionAnsowerCount(String id) {
//        Query query=new Query();
//        query.addCriteria(Criteria.where("id").is(id));
//        Update update=new Update();
//        update.inc("answerCount");
//        mongoTemplate.upsert(query,update, Question.class);
//    }

    @Override
    protected void addListCondition(Criteria criteria, Object[] data) {
        criteria.and("question.id").is(data[0]);
        if(Objects.nonNull(data[1])){
            criteria.and("content").regex(data[1].toString());
        }
        if(Objects.nonNull(data[2])){
            criteria.and("state").is(data[2]);
        }
    }

    @Override
    protected Class<QuestionAnswer> getEntityClass() {
        return QuestionAnswer.class;
    }

}

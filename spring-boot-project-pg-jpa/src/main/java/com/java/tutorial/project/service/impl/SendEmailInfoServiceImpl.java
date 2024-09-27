package com.java.tutorial.project.service.impl;

import com.java.tutorial.project.domain.QCreatorInfo;
import com.java.tutorial.project.domain.QSendEmailInfoEntity;
import com.java.tutorial.project.domain.SendEmailInfoEntity;
import com.java.tutorial.project.repository.SendEmailInfoRepository;
import com.java.tutorial.project.service.SendEmailInfoService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.javers.spring.annotation.JaversAuditable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class SendEmailInfoServiceImpl implements SendEmailInfoService {

    @Resource
    private JPAQueryFactory jpaQueryFactory;

    private final QSendEmailInfoEntity qSendEmailInfoEntity = QSendEmailInfoEntity.sendEmailInfoEntity;

    @Resource
    private SendEmailInfoRepository sendEmailInfoRepository;

    @Override
    public void saveSendEmailInfoEntity(SendEmailInfoEntity sendEmailInfoEntity) {
        sendEmailInfoRepository.save(sendEmailInfoEntity);
    }

    @Override
    public SendEmailInfoEntity getSendEmailInfoEntityById(Integer id) {
        return sendEmailInfoRepository.findById(id).orElse(new SendEmailInfoEntity());
    }

    @JaversAuditable
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSendEmailInfoEntity(SendEmailInfoEntity sendEmailInfoEntity) {

        sendEmailInfoRepository.updateAllById(sendEmailInfoEntity.getFromAccount(), sendEmailInfoEntity.getToAccount(), sendEmailInfoEntity.getContent(), sendEmailInfoEntity.getSendTime(), sendEmailInfoEntity.getEmailId(), sendEmailInfoEntity.getConversationId(), sendEmailInfoEntity.getId());


//        jpaQueryFactory.update(qSendEmailInfoEntity).
//                set(qSendEmailInfoEntity.fromAccount, sendEmailInfoEntity.getFromAccount())
//                .set(qSendEmailInfoEntity.toAccount, sendEmailInfoEntity.getToAccount())
//                .set(qSendEmailInfoEntity.content, sendEmailInfoEntity.getContent())
//                .set(qSendEmailInfoEntity.sendTime, sendEmailInfoEntity.getSendTime())
//                .set(qSendEmailInfoEntity.emailId, sendEmailInfoEntity.getEmailId())
//                .set(qSendEmailInfoEntity.conversationId, sendEmailInfoEntity.getConversationId())
//                .where(qSendEmailInfoEntity.id.eq(sendEmailInfoEntity.getId())).execute();
    }
}

package com.java.tutorial.project.service.impl;

import com.java.tutorial.project.domain.EmailRepliedInfoEntity;
import com.java.tutorial.project.repository.EmailRepliedInfoRepository;
import com.java.tutorial.project.service.EmailRepliedInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author meta
 */
@Slf4j
@Service
public class EmailRepliedInfoServiceImpl implements EmailRepliedInfoService {

    @Resource
    private EmailRepliedInfoRepository emailRepliedInfoRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(EmailRepliedInfoEntity emailRepliedInfoEntity) {
        emailRepliedInfoRepository.save(emailRepliedInfoEntity);

    }

}

package com.java.tutorial.project.service.impl;

import com.java.tutorial.project.domain.CreatorInfo;
import com.java.tutorial.project.domain.QCreatorInfo;
import com.java.tutorial.project.repository.CreatorInfoRepository;
import com.java.tutorial.project.service.CreatorInfoService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.javers.spring.annotation.JaversAuditable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author meta
 */
@Slf4j
@Service
public class CreatorInfoServiceImpl implements CreatorInfoService {

    @Resource
    private CreatorInfoRepository creatorInfoRepository;

    @Resource
    private JPAQueryFactory jpaQueryFactory;

    private final QCreatorInfo qCreatorInfo = QCreatorInfo.creatorInfo;

    @Override
    public CreatorInfo getCreatorInfoByCreatorId(String creatorId) {
        return creatorInfoRepository.findById(creatorId).orElse(null);
    }

    @Override
    public CreatorInfo queryDslCreatorInfoByCreatorId(String creatorId) {
        return jpaQueryFactory.select(qCreatorInfo).from(qCreatorInfo).where(qCreatorInfo.creatorId.eq(creatorId))
            .fetchFirst();
    }

    @Override
    public Page<CreatorInfo> findByCountry(String country, Integer pageNumber) {

        Pageable pageable = Pageable.ofSize(10);
        pageable.withPage(pageNumber);

        return creatorInfoRepository.findByCountryOrderByAddTimeDesc(country, pageable);
    }

    @Override
    public List<String> queryAllCreatorId() {

        List<String> fetch2 = jpaQueryFactory.select(qCreatorInfo.creatorId).from(qCreatorInfo).fetch();

        return fetch2;
    }

    @Override
    @JaversAuditable
    @Transactional(rollbackFor = Exception.class)
    public void updateCreatorInfo(CreatorInfo creatorInfo) {
        creatorInfoRepository.updateSetCreatorNameByCreatorId(creatorInfo.getCreatorName(), creatorInfo.getCreatorId());
    }

    public void testQueryDsl() {

        // 查询全部
        List<CreatorInfo> fetch = jpaQueryFactory.select(qCreatorInfo).from(qCreatorInfo).fetch();
        List<CreatorInfo> fetch1 = jpaQueryFactory.selectFrom(qCreatorInfo).fetch();

        // 查询某一个字段
        List<String> fetch2 = jpaQueryFactory.select(qCreatorInfo.creatorId).from(qCreatorInfo).fetch();

        // 去重查询
        List<String> fetch3 = jpaQueryFactory.selectDistinct(qCreatorInfo.country).from(qCreatorInfo).fetch();

        // 获取首个查询结果
        CreatorInfo creatorInfo1 = jpaQueryFactory.selectFrom(qCreatorInfo).fetchFirst();

        // 获取唯一的查询结果
        // 当fetchOne查询出来是一个多个数据集合，将会抛出 ‘com.querydsl.core.NonUniqueResultException’  错误
        CreatorInfo creatorInfo2 =
            jpaQueryFactory.select(qCreatorInfo).from(qCreatorInfo).where(qCreatorInfo.creatorName.eq("张杰 2"))
                .fetchOne();
    }

    @Override
    @JaversAuditable
    @Transactional(rollbackFor = Exception.class)
    public void updateQueryDslCreatorInfo(CreatorInfo creatorInfo) {
        long execute = jpaQueryFactory.update(qCreatorInfo)
            .set(qCreatorInfo.creatorName, creatorInfo.getCreatorName())
            .where(qCreatorInfo.creatorId.eq(creatorInfo.getCreatorId())).execute();
        log.info("updateCreatorInfo execute: {}", execute);
    }

}

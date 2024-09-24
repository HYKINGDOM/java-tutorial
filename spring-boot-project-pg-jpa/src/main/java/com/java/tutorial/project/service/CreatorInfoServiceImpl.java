package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.CreatorInfo;
import com.java.tutorial.project.repository.CreatorInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author meta
 */
@Slf4j
@Service
public class CreatorInfoServiceImpl implements CreatorInfoService{

    @Resource
    private CreatorInfoRepository creatorInfoRepository;

    @Override
    public CreatorInfo getCreatorInfoByCreatorId(String creatorId) {
        return creatorInfoRepository.findById(creatorId).orElse(null);
    }

    @Override
    public Page<CreatorInfo> findByCountry(String country, Integer pageNumber) {

        Pageable pageable = Pageable.ofSize(10);
        pageable.withPage(pageNumber);

        return creatorInfoRepository.findByCountryOrderByAddTimeDesc(country, pageable);
    }

}

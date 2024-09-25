package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.CreatorInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CreatorInfoService {



    CreatorInfo getCreatorInfoByCreatorId(String creatorId);

    CreatorInfo queryDslCreatorInfoByCreatorId(String creatorId);

    Page<CreatorInfo> findByCountry(String country, Integer pageNumber);


    List<String> queryAllCreatorId();

    void updateCreatorInfo(CreatorInfo creatorInfo);

    void updateQueryDslCreatorInfo(CreatorInfo creatorInfo);
}

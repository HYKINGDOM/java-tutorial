package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.CreatorInfo;
import org.springframework.data.domain.Page;

public interface CreatorInfoService {



    CreatorInfo getCreatorInfoByCreatorId(String creatorId);

    Page<CreatorInfo> findByCountry(String country, Integer pageNumber);
}

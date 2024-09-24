package com.java.tutorial.project.repository;

import com.java.tutorial.project.domain.CreatorInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author meta
 */
@Repository
public interface CreatorInfoRepository extends JpaRepository<CreatorInfo,String> {


    Page<CreatorInfo> findByCountryOrderByAddTimeDesc(String country, Pageable pageable);
}

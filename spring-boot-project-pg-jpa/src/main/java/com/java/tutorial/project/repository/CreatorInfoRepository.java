package com.java.tutorial.project.repository;

import com.java.tutorial.project.domain.CreatorInfo;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author meta
 */
@Repository
@JaversSpringDataAuditable
public interface CreatorInfoRepository
    extends JpaRepository<CreatorInfo, String>, QuerydslPredicateExecutor<CreatorInfo>, CrudRepository<CreatorInfo, String> {

    Page<CreatorInfo> findByCountryOrderByAddTimeDesc(String country, Pageable pageable);

    @Modifying
    @Query("update CreatorInfo set creatorName = :creatorName where creatorId = :creatorId")
    void updateSetCreatorNameByCreatorId(@Param("creatorName") String creatorName,@Param("creatorId") String creatorId);
}

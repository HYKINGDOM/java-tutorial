package com.java.tutorial.project.repository;

import com.java.tutorial.project.domain.SendEmailInfoEntity;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@JaversSpringDataAuditable
public interface SendEmailInfoRepository
    extends JpaRepository<SendEmailInfoEntity, Integer>, QuerydslPredicateExecutor<SendEmailInfoEntity> {

    @Modifying
    @Query(
        "update SendEmailInfoEntity set fromAccount = :fromAccount, toAccount = :toAccount, content = :content, sendTime = :sendTime, emailId = :emailId, conversationId = :conversationId where id = :id")
    void updateAllById(@Param("fromAccount") String fromAccount, @Param("toAccount") String toAccount,
        @Param("content") String content, @Param("sendTime") LocalDateTime sendTime, @Param("emailId") String emailId,
        @Param("conversationId") String conversationId, @Param("id") Integer id);
}

package com.java.tutorial.project.repository;

import com.java.tutorial.project.domain.EmailRepliedInfoEntity;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : meta
 */
@Repository
@JaversSpringDataAuditable
public interface EmailRepliedInfoRepository
    extends JpaRepository<EmailRepliedInfoEntity, Long>{

}

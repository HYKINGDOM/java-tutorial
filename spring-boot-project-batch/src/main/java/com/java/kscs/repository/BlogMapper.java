package com.java.kscs.repository;


import com.java.kscs.dto.BlogInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 *
 */

@Repository
public interface BlogMapper extends JpaRepository<BlogInfo, String> {


}
package com.java.demo.repository;

import com.java.demo.dto.BlogInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */

@Repository
public interface BlogMapper extends JpaRepository<BlogInfo, String> {

}

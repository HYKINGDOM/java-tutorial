package com.java.tutorial.project.infrastructure.mapper;

import com.java.tutorial.project.infrastructure.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapper extends JpaRepository<SysUser, Long> {
}

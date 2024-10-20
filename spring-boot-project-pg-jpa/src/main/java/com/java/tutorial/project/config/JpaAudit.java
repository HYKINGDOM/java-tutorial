package com.java.tutorial.project.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 审计字段的当前操作人信息
 *
 * @author meta
 * @CreatedBy
 * @CreatedDate
 * @LastModifiedBy
 * @LastModifiedDate
 * @CreatedDate
 */
@Component
public class JpaAudit implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Long userId = 1234242L;
        return Optional.of(userId);
    }
}

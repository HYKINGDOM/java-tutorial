package com.java.data.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * mongodb 审计字段配置
 * @author HY
 */
@Component
public class MongoDBAudit implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String user = "System";
        return Optional.of(user);
    }
}

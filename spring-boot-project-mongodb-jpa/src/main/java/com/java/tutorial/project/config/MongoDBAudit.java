package com.java.tutorial.project.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
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

package com.java.tutorial.project.config;


import com.java.tutorial.project.constant.TogglzFeatures;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;

@Configuration
public class FeatureProviderConfig {

    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(TogglzFeatures.class);
    }
}

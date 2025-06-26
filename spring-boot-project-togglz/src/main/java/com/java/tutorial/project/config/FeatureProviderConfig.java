package com.java.tutorial.project.config;


@Configuration
public class FeatureProviderConfig {

    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(TogglzFeatures.class);
    }
}

package com.java.tutorial.project.config;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author HY
 */
@Slf4j
@Configuration
public class KiaSessionConfig {

    private static final String RULES_PATH = "rules/";

    @Bean
    public KieFileSystem kieFileSystem() {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
        }
        return kieFileSystem;
    }

    private Resource[] getRuleFiles() {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Bean
    public KieContainer kieContainer() {
        final KieRepository kieRepository = getKieServices().getRepository();
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
        KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();
        return getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    @Bean
    public KieBase kieBase() {
        return kieContainer().getKieBase();
    }

    @Bean
    public KieSession kieSession() {
        return kieContainer().newKieSession();
    }
}


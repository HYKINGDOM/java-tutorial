package com.java.tutorial.project.config;

import com.java.tutorial.project.service.SmsPlugin;
import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.config.EnablePluginRegistries;

@EnablePluginRegistries(SmsPlugin.class)
@Configuration
public class SmsPluginActiveConfig {

}





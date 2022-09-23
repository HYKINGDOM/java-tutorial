package com.java.tutorial.project.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;

public class MyCacheManager implements CacheManager {
    @Override
    public Cache getCache(String name) {
        return null;
    }

    @Override
    public Collection<String> getCacheNames() {
        return null;
    }
}

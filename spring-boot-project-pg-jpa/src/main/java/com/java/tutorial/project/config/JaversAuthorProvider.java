package com.java.tutorial.project.config;

import org.javers.spring.auditable.AuthorProvider;


/**
 * JaversAuthorProvider 实现类 获取当前操作人的用户名
 * @author meta
 */
public class JaversAuthorProvider implements AuthorProvider {

    @Override
    public String provide() {
        return "admin";
    }
}


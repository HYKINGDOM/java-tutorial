package com.java.tutorial.project.service;

/**
 * @author meta
 */
public class CompletableTaskImpl implements CompletableTask {

    @Override
    public Object execute(Object task) {
        return null;
    }

    @Override
    public void secusess() {
        CompletableTask.super.secusess();
    }

    @Override
    public void error() {
        CompletableTask.super.error();
    }
}

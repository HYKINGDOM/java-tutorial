package com.java.tutorial.project.config;

@FunctionalInterface
public interface TaskDecorator {

    /**
     * Decorate the given {@code Runnable}, returning a potentially wrapped
     * {@code Runnable} for actual execution.
     * @param runnable the original {@code Runnable}
     * @return the decorated {@code Runnable}
     */
    Runnable decorate(Runnable runnable);

}
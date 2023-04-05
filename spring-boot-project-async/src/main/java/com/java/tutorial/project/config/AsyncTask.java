package com.java.tutorial.project.config;

import com.google.common.collect.Lists;
import com.java.tutorial.project.domain.User;
import com.java.tutorial.project.mapper.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;

/**
 * @author HY
 */
@Component
@Slf4j
public class AsyncTask {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Executor asyncTaskExecutor;

    public CompletableFuture<List<User>> getUsername(List<Integer> ids) {
        return CompletableFuture
                .supplyAsync(() -> userRepository.getUsers(ids), asyncTaskExecutor)
                .exceptionally(throwable -> {
                    log.info("异常：{}", throwable.getMessage());
                    return Lists.newArrayList();
                });
    }

    /**
     * 这里可以执行不同的sql去获取不同的数据，然后最后进行组装，一个sql一个方法
     *
     * @param ids
     * @return
     */
    public CompletableFuture<List<User>> getAge(List<Integer> ids) {
        return CompletableFuture.supplyAsync(() -> {
            //具体要执行的逻辑
            List<User> userParam = new ArrayList<>();
            try {
                userParam = userRepository.getUsers(ids);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                e.printStackTrace();
            }
            return userParam;
        }, asyncTaskExecutor).exceptionally(throwable -> {
            log.info("异常：{}", throwable.getMessage());
            return Lists.newArrayList();
        });
    }
}

package com.java.tutorial.project.service;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.java.tutorial.project.infrastructure.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Slf4j
@Service
public class TaskServiceImpl implements TaskService {


    @Override
    public List<String> combindStrList(String str) {


        CompletableFuture<List<String>> future1 = CompletableFuture.supplyAsync(() -> taskStringList01(str));


        CompletableFuture<List<String>> future2 = CompletableFuture.supplyAsync(() -> taskStringList02(Long.valueOf(str)));


        List<String> stringList = Lists.newArrayList();


        CompletableFuture<List<String>> result = future1.thenCombine(future2, (t, u) -> {
            stringList.addAll(t);
            stringList.addAll(u);
            return stringList;
        }).exceptionally(throwable -> Lists.newArrayList());

        List<String> strings = Lists.newArrayList();

        try {
            strings = result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return strings;
    }

    @Override
    public List<String> serialStrList(String str) {


        List<String> strings = taskStringList01(str);

        List<String> strings1 = taskStringList02(Long.valueOf(str));

        List<String> objects = Lists.newArrayList();

        objects.addAll(strings1);
        objects.addAll(strings);

        return objects;
    }

    @Override
    public List<String> taskStringList01(String str) {

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<String> strings = Lists.newArrayList();
        strings.add(str);

        return strings;
    }

    @Override
    public List<String> taskStringList02(Long ids) {
        try {
            Thread.sleep(RandomUtil.randomLong(5000L));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<String> strings = Lists.newArrayList();
        strings.add(String.valueOf(ids));

        return strings;
    }

    @Override
    public List<String> taskStringList03(Integer num) {
        try {
            Thread.sleep(RandomUtil.randomLong(10000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<String> strings = Lists.newArrayList();
        strings.add(String.valueOf(num));

        return strings;
    }

    @Override
    public List<String> taskStringList04(String str) {
        try {
            Thread.sleep(RandomUtil.randomLong(10000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<String> strings = Lists.newArrayList();
        strings.add(str);

        return strings;
    }

    @Override
    public User taskCreateUser01(String str) {
        return null;
    }

    @Override
    public User taskCreateUser03(String str) {
        return null;
    }

    @Override
    public User taskCreateUser04(String str) {
        return null;
    }
}

package com.java.tutorial.project.controller;

import com.google.common.collect.Lists;
import com.java.coco.utils.TraceIDUtil;
import com.java.tutorial.project.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Slf4j
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskServiceController {

    private final TaskService taskService;

    private final Executor asyncTaskExecutor;

    @GetMapping("/get")
    public List<String> getLisTask(@RequestParam String str) {
        long currentTimeMillis = System.currentTimeMillis();
        List<String> strings = taskService.combindStrList(str);

        log.info("getLisTask执行时间： {}", System.currentTimeMillis() - currentTimeMillis);
        return strings;
    }

    @GetMapping("/getStr")
    public List<String> getSerialStrList(@RequestParam String str) {
        long currentTimeMillis = System.currentTimeMillis();
        List<String> strings = taskService.serialStrList(str);
        log.info("serialStrList执行时间： {}", System.currentTimeMillis() - currentTimeMillis);
        return strings;
    }

    @GetMapping("/getExecutor")
    public List<String> getExecutor(@RequestParam String str) {

        log.info("getExecutor： {}", TraceIDUtil.getTraceId());


        for (int i = 0; i < 10; i++) {
            CompletableFuture.runAsync(() -> taskService.taskStringList04(str), asyncTaskExecutor);
        }
        return Lists.newArrayList(str);
    }

}

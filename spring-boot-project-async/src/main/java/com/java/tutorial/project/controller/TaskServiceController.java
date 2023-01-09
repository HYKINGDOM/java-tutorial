package com.java.tutorial.project.controller;


import com.java.tutorial.project.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskServiceController {


    private final TaskService taskService;


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


}

package com.java.tutorial.project.controller;

import cn.hutool.log.level.TraceLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author kscs
 */
@Slf4j
@RestController
@RequestMapping("/web-async")
public class WebAsyncTaskController {

    @GetMapping(value = "/async/click")
    public WebAsyncTask<String> webAsyncClick() {
        log.info("外部线程：" + Thread.currentThread().getName());
        Callable<String> result = () -> {
            log.info("副线程返回,内部线程开始：" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                // TODO: handle exception
            }
            log.info("副线程返回,内部线程返回：" + Thread.currentThread().getName());
            return "success";
        };

        // WebAsyncTask设置等待3秒就执行超时回调
        WebAsyncTask<String> wat = new WebAsyncTask<String>(4500L, result);
        wat.onTimeout(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // TODO Auto-generated method stub
                return "超时";
            }
        });
        return wat;
    }
}

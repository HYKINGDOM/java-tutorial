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

    /**
     * INFO 28588 --- [nio-8089-exec-4] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
     * INFO 28588 --- [nio-8089-exec-4] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
     * INFO 28588 --- [nio-8089-exec-4] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
     * INFO 28588 --- [nio-8089-exec-4] c.j.t.p.c.WebAsyncTaskController         : 外部线程：http-nio-8089-exec-4
     * INFO 28588 --- [   user-async-1] c.j.t.p.c.WebAsyncTaskController         : 副线程返回,内部线程开始：user-async-1
     * INFO 28588 --- [   user-async-1] c.j.t.p.c.WebAsyncTaskController         : 副线程返回,内部线程返回：user-async-1
     * INFO 28588 --- [nio-8089-exec-3] c.j.t.p.c.WebAsyncTaskController         : 异步任务执行完毕
     * @return
     */
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
        wat.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.info("异步任务执行完毕");
            }
        });

        wat.onError(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.error("异步任务执行出错");
                return null;
            }
        });
        return wat;
    }
}

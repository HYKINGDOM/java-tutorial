package com.java.tutorial.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.exception.ExceptionUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * stream 测试
 *
 * @author meta
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/stream")
public class EmitterController {

    //定义一个全局的变量，用来存储DeferredResult对象
    private Map<String, DeferredResult<String>> deferredResultMap = new ConcurrentHashMap<>();

    @GetMapping("/testDeferredResult")
    public DeferredResult<String> testDeferredResult() {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        deferredResultMap.put("test", deferredResult);
        return deferredResult;
    }

    @GetMapping("/events")
    public ResponseEntity<ResponseBodyEmitter> handle() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(0L);
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(emitter);
    }

    @GetMapping("/events-test1")
    public ResponseEntity<StreamingResponseBody> stream() {
        StreamingResponseBody stream = out -> {
            String message = "stream_test_";
            for (int i = 0; i < message.length(); i++) {
                try {
                    out.write((message + i).getBytes());
                    out.write("\r\n".getBytes());
                    //调用一次flush就会像前端写入一次数据
                    out.flush();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    log.error("error:{}", ExceptionUtil.stacktraceToOneLineString(e));
                }
            }
        };
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(stream);
    }

}

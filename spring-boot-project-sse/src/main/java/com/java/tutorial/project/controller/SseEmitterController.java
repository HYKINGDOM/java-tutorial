package com.java.tutorial.project.controller;

import static com.alibaba.fastjson2.util.DateUtils.DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH;

import com.alibaba.fastjson2.util.DateUtils;
import com.java.coco.common.Result;
import com.java.tutorial.project.client.MsgClient;
import com.java.tutorial.project.common.constant.LimitMessageConstant;
import com.java.tutorial.project.common.entity.ConnectionInfo;
import com.java.tutorial.project.common.vo.MessageVo;
import com.java.tutorial.project.service.ConnectionInfoService;
import com.java.tutorial.project.service.SseEmitterService;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * SSE长链接
 * @author meta
 */
@RestController
@RequestMapping("/sse")
public class SseEmitterController {
    @Resource
    private SseEmitterService sseEmitterService;
    @Resource
    private ConnectionInfoService connectionInfoService;
    @Resource
    private MsgClient msgClient;

    @CrossOrigin
    @GetMapping("/createConnect")
    public SseEmitter createConnect(@RequestParam String clientId, @RequestParam(required = false) Integer type) {
        return sseEmitterService.createConnect(clientId, type);
    }

    @CrossOrigin
    @PostMapping("/broadcast")
    public void sendMessageToAllClient(@RequestBody(required = false) String msg) {
        sseEmitterService.sendMessageToAllClient(msg);
    }

    @CrossOrigin
    @PostMapping("/sendMessage")
    public void sendMessageToOneClient(@RequestBody MessageVo messageVo) {
        if (messageVo.getClientId().isEmpty()) {
            return;
        }
        sseEmitterService.sendMessageToOneClient(messageVo.getClientId(), messageVo.getData(), messageVo.getType());
    }

    @CrossOrigin
    @PostMapping("/sendMessageToManyClient")
    public void sendMessageToManyClient(@RequestBody MessageVo messageVo) {
        if (messageVo.getClientId().isEmpty()) {
            return;
        }
        sseEmitterService.sendMessageToManyClient(messageVo);
    }

    @CrossOrigin
    @GetMapping("/closeConnect")
    public Result<?> closeConnect(@RequestParam String clientId) {
        sseEmitterService.closeConnect(clientId);
        return Result.success();
    }

    @CrossOrigin
    @GetMapping("/test")
    public void test(@RequestParam String clientId) {
        String content = String.format(LimitMessageConstant.content1, clientId,
            DateUtils.format(new Date(), DATE_TIME_FORMAT_19_DASH.name()));
        String title = "服务端推送消息失败";
        msgClient.personSend(title, content, 1016L);
    }

    @CrossOrigin
    @GetMapping("/getAllConnection")
    public Set<String> getAllConnection() {
        return sseEmitterService.getAllClient();
    }

    @CrossOrigin
    @GetMapping("/getAllConnectionInfo")
    public List<ConnectionInfo> getAllConnectionInfo() {
        return connectionInfoService.listAll();
    }
}

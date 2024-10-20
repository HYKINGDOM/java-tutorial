package com.java.tutorial.project.controller;


import com.java.tutorial.project.common.vo.ContentVo;
import com.java.tutorial.project.infrastucture.entity.ConnectionEntity;
import com.java.tutorial.project.common.vo.MessageVo;
import com.java.tutorial.project.service.ConnectionInfoService;
import com.java.tutorial.project.service.SseEmitterService;
import com.java.tutorial.project.common.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import java.util.List;
import java.util.Set;

/**
 * SSE长链接
 *
 * @author meta
 */
@CrossOrigin
@RestController
@RequestMapping("/sse")
public class SseEmitterController {

    @Resource
    private SseEmitterService sseEmitterService;

    @Resource
    private ConnectionInfoService connectionInfoService;

    @GetMapping("/createConnect")
    public SseEmitter createConnect(@RequestParam(required = false) String clientId,
        @RequestParam(required = false) Integer type) {
        return sseEmitterService.createConnect(clientId, type);
    }

    @PostMapping("/send-message-to-all")
    public void sendMessageToAllClient(@RequestBody ContentVo contentVo) {
        sseEmitterService.sendMessageToAllClient(contentVo);
    }

    @PostMapping("/sendMessage")
    public void sendMessageToOneClient(@RequestBody MessageVo messageVo) {
        if (messageVo.getClientId().isEmpty()) {
            return;
        }
        sseEmitterService.sendMessageToOneClient(messageVo);
    }

    @PostMapping("/sendMessageToManyClient")
    public void sendMessageToManyClient(@RequestBody MessageVo messageVo) {
        if (messageVo.getClientId().isEmpty()) {
            return;
        }
        sseEmitterService.sendMessageToManyClient(messageVo);
    }

    @GetMapping("/closeConnect")
    public Result<?> closeConnect(@RequestParam String clientId) {
        sseEmitterService.closeConnect(clientId);
        return Result.success();
    }

    @GetMapping("/getAllConnection")
    public Set<String> getAllConnection() {
        return sseEmitterService.getAllClient();
    }

    @GetMapping("/getAllConnectionInfo")
    public List<ConnectionEntity> getAllConnectionInfo() {
        return connectionInfoService.listAll();
    }
}

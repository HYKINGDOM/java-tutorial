package com.java.tutorial.project.socket;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin("*")
@Controller
@RequestMapping("/socket")
public class SocketOpsController {

    @Resource
    private WebSocketServer webSocket;

    @RequestMapping("/index")
    public String sse() {
        return "socket";
    }

    /**
     * 变更数据
     */
    @GetMapping(path = "publish")
    @ResponseBody
    public String publish(String message, String userId) {
        //创建业务消息信息
        JSONObject obj = new JSONObject();
        webSocket.sendOneMessage(userId, message);
        return "success";
    }
}


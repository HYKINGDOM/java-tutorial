package com.java.tutorial.project.controller;

import com.alibaba.fastjson2.JSON;
import com.java.tutorial.project.domain.OrderContext;
import com.java.tutorial.project.domain.OrderParam;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class RunOrderFlowTestController {

    @Resource
    private FlowExecutor flowExecutor;

    @GetMapping("/runOrderFlowTest")
    public String runOrderFlowTest() {
        OrderParam orderParam =
            OrderParam.builder().orderType(1).userId("coderacademy").userName("码农Academy").build();
        LiteflowResponse response = flowExecutor.execute2Resp("order_handle", orderParam, OrderContext.class);
        OrderContext orderContext = response.getContextBean(OrderContext.class);
        System.out.println(JSON.toJSONString(orderContext));

        return "success";
    }
}

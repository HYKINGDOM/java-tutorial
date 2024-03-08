package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.OrderContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.annotation.LiteflowMethod;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.enums.LiteFlowMethodEnum;
import com.yomahub.liteflow.enums.NodeTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@LiteflowComponent
public class ToBHandleCmp {

    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS, nodeType = NodeTypeEnum.COMMON, nodeId = "order_b",
        nodeName = "BNodeComponent")
    public void processB(NodeComponent nodeComponent) {
        // 获取OrderContext上下文
        OrderContext orderContext = nodeComponent.getContextBean(OrderContext.class);
        log.info("执行处理业务节点B");
        List<String> executeRuleList = Optional.ofNullable(orderContext.getExecuteRuleList()).orElse(new ArrayList<>());
        executeRuleList.add("BNodeComponent");
        orderContext.setExecuteRuleList(executeRuleList);
        orderContext.setOrderId("TOB11111");
    }

    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS, nodeType = NodeTypeEnum.COMMON, nodeId = "order_e",
        nodeName = "ENodeComponent")
    public void processE(NodeComponent nodeComponent) {
        // 获取OrderContext上下文
        OrderContext orderContext = nodeComponent.getContextBean(OrderContext.class);
        log.info("执行处理业务节点E");
        List<String> executeRuleList = Optional.ofNullable(orderContext.getExecuteRuleList()).orElse(new ArrayList<>());
        executeRuleList.add("ENodeComponent");
        orderContext.setExecuteRuleList(executeRuleList);
    }
}


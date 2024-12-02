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

/**
 * @author meta
 */
@Slf4j
@LiteflowComponent
public class ToCHandleCmp {

    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS, nodeType = NodeTypeEnum.COMMON, nodeId = "order_a", nodeName = "ANodeComponent")
    public void processA(NodeComponent nodeComponent){
        // 获取OrderContext上下文
        OrderContext orderContext = nodeComponent.getContextBean(OrderContext.class);
        log.info("执行处理业务节点A");
        final List<String> executeRuleList = Optional.ofNullable(orderContext.getExecuteRuleList()).orElse(new ArrayList<>());
        executeRuleList.add("ANodeComponent");
        orderContext.setExecuteRuleList(executeRuleList);
        orderContext.setOrderId("TOC11111");
    }

    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS, nodeType = NodeTypeEnum.COMMON, nodeId = "order_d", nodeName = "DNodeComponent")
    public void processD(NodeComponent nodeComponent){
        log.info("执行处理业务节点D");
        // 获取OrderContext上下文
        OrderContext orderContext = nodeComponent.getContextBean(OrderContext.class);
        final List<String> executeRuleList = Optional.ofNullable(orderContext.getExecuteRuleList()).orElse(new ArrayList<>());
        executeRuleList.add("DNodeComponent");
        orderContext.setExecuteRuleList(executeRuleList);
    }


    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS, nodeType = NodeTypeEnum.COMMON, nodeId = "order_f", nodeName = "FNodeComponent")
    public void processF(NodeComponent nodeComponent) {
        // 获取OrderContext上下文
        OrderContext orderContext = nodeComponent.getContextBean(OrderContext.class);
        log.info("执行处理业务节点F");
        final List<String> executeRuleList = Optional.ofNullable(orderContext.getExecuteRuleList()).orElse(new ArrayList<>());
        executeRuleList.add("FNodeComponent");
        orderContext.setExecuteRuleList(executeRuleList);
    }

}


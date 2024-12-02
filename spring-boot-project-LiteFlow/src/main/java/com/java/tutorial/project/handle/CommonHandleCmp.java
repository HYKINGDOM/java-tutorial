package com.java.tutorial.project.handle;

import com.java.tutorial.project.domain.OrderContext;
import com.java.tutorial.project.domain.OrderParam;
import com.java.tutorial.project.domain.OrderTypeEnum;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.annotation.LiteflowMethod;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.enums.LiteFlowMethodEnum;
import com.yomahub.liteflow.enums.NodeTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author meta
 */
@Slf4j
@LiteflowComponent
public class CommonHandleCmp {

    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS_SWITCH, nodeType = NodeTypeEnum.SWITCH, nodeId = "order_x",
        nodeName = "获取订单链路")
    public String processOrderType(NodeComponent nodeComponent) {
        log.info("获取订单链路");
        // 获取流程入参参数
        OrderParam orderParam = nodeComponent.getRequestData();
        // 订单类型
        Integer orderType = orderParam.getOrderType();
        String chainId = OrderTypeEnum.getChainId(orderType);
        log.info("获取订单链路: {}", chainId);
        return chainId;
    }

    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS, nodeType = NodeTypeEnum.COMMON, nodeId = "order_c",
        nodeName = "CNodeComponent")
    public void processC(NodeComponent nodeComponent) {
        log.info("执行处理业务节点C");
        // 获取OrderContext上下文
        OrderContext orderContext = nodeComponent.getContextBean(OrderContext.class);
        List<String> executeRuleList = Optional.ofNullable(orderContext.getExecuteRuleList()).orElse(new ArrayList<>());
        executeRuleList.add("CNodeComponent");
        orderContext.setExecuteRuleList(executeRuleList);
    }

    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS_SWITCH, nodeType = NodeTypeEnum.SWITCH, nodeId = "order_k",
        nodeName = "KNodeComponent")
    public boolean processK(NodeComponent nodeComponent) {
        log.info("执行处理业务节点K");
        // 获取OrderContext上下文
        OrderContext orderContext = nodeComponent.getContextBean(OrderContext.class);
        List<String> executeRuleList = Optional.ofNullable(orderContext.getExecuteRuleList()).orElse(new ArrayList<>());
        executeRuleList.add("KNodeComponent");
        orderContext.setExecuteRuleList(executeRuleList);
        return StringUtils.isBlank(orderContext.getOrderId());
    }

    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS, nodeType = NodeTypeEnum.COMMON, nodeId = "order_h",
        nodeName = "HNodeComponent")
    public void processH(NodeComponent nodeComponent) {
        // 获取OrderContext上下文
        OrderContext orderContext = nodeComponent.getContextBean(OrderContext.class);
        log.info("执行处理业务节点H");
        List<String> executeRuleList = Optional.ofNullable(orderContext.getExecuteRuleList()).orElse(new ArrayList<>());
        executeRuleList.add("HNodeComponent");
        orderContext.setExecuteRuleList(executeRuleList);
    }

}


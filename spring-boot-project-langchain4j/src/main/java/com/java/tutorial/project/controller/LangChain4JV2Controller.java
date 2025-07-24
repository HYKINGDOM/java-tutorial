package com.java.tutorial.project.controller;

import com.alibaba.fastjson.JSON;
import com.java.tutorial.project.tool.WorkOrderTool;
import com.java.tutorial.project.util.Result;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.TokenUsage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * LangChain4J测试V2
 *
 * @author meta
 */
@Slf4j
@RequestMapping("/langchain4j/V2")
@RestController
public class LangChain4JV2Controller {

    @Resource
    private ChatModel chatModelQwen;

    @Resource
    private ChatModel chatModelDeepSeek;

    @Resource
    private WorkOrderTool workOrderTool;

    String text = """
        In 1968, amidst the fading echoes of Independence Day,
        a child named John arrived under the calm evening sky.
        This newborn, bearing the surname Doe, marked the start of a new journey.
        He was welcomed into the world at 345 Whispering Pines Avenue
        a quaint street nestled in the heart of Springfield
        an abode that echoed with the gentle hum of suburban dreams and aspirations.
        """;

    /**
     * Qwen 模型
     *
     * @param prompt
     * @return
     */
    @GetMapping(value = "/langchain4j/Qwen")
    public Result<ChatResponse> simpleChat(@RequestParam(value = "prompt", defaultValue = "你是谁?") String prompt) {

        AiMessage aiMessage = AiMessage.from(prompt);

        ChatResponse result = chatModelQwen.chat(aiMessage);

        log.info("通过langchain4j调用 Qwen 模型返回结果：{}", JSON.toJSONString(result));

        TokenUsage tokenUsage = result.tokenUsage();
        log.info("Qwen 模型token消耗返回结果：{}", JSON.toJSONString(tokenUsage));

        return Result.success(result);
    }

    @GetMapping(value = "/langchain4j/ChatRequest/Qwen")
    public Result<Object> simpleChatRequest(
        @RequestParam(value = "prompt", defaultValue = "工单类型是视频,帮我生成工单编号") String prompt) {

        List<ToolSpecification> toolSpecifications = ToolSpecifications.toolSpecificationsFrom(WorkOrderTool.class);

        ChatRequest request = ChatRequest.builder().messages(UserMessage.from("工单类型是视频,帮我生成工单编号"))
            .toolSpecifications(toolSpecifications).build();

        ChatResponse chatResponse = chatModelQwen.chat(request);
        AiMessage result = chatResponse.aiMessage();
        boolean hasToolExecutionRequests = result.hasToolExecutionRequests();
        System.out.println(hasToolExecutionRequests);
        log.info("通过langchain4j ChatRequest 调用 Qwen 模型返回结果：{}", JSON.toJSONString(result));

        // 处理工具调用
        if (hasToolExecutionRequests) {
            List<ToolExecutionResultMessage> toolExecutionResultMessages = new ArrayList<>();
            for (ToolExecutionRequest toolExecutionRequest : result.toolExecutionRequests()) {
                // 执行工具方法
                String toolResult = executeTool(toolExecutionRequest);
                toolExecutionResultMessages.add(ToolExecutionResultMessage.from(toolExecutionRequest, toolResult));
            }

            // 将工具执行结果发送回模型
            ChatRequest secondRequest = ChatRequest.builder()
                .messages(UserMessage.from("工单类型是视频,帮我生成工单编号"), result)
                .toolSpecifications(toolSpecifications).build();

            ChatResponse secondResponse = chatModelQwen.chat(secondRequest);
            return Result.success(secondResponse.aiMessage().text());
        }

        return Result.success(result);
    }

    private String executeTool(ToolExecutionRequest toolExecutionRequest) {
        // 根据工具名称执行相应的方法
        if ("generateWorkOrderCode".equals(toolExecutionRequest.name())) {
            String arguments = toolExecutionRequest.arguments();
            // 解析参数，这里简化处理，实际应该使用JSON解析
            String workOrderType = "视频"; // 简化处理，实际应该从arguments中解析
            return workOrderTool.generateWorkOrderCode(workOrderType);
        }
        return "Unknown tool: " + toolExecutionRequest.name();
    }

    /**
     * DeepSeek
     *
     * @param prompt
     * @return
     */
    @GetMapping(value = "/langchain4j/deepseek")
    public String deepseekCall(@RequestParam(value = "prompt", defaultValue = "你是谁?") String prompt) {

        AiMessage aiMessage = AiMessage.from(prompt);

        ChatResponse result = chatModelQwen.chat(aiMessage);

        log.info("通过langchain4j调用 Qwen 模型返回结果：{}", JSON.toJSONString(result));

        TokenUsage tokenUsage = result.tokenUsage();
        log.info("Qwen 模型token消耗返回结果：{}", JSON.toJSONString(tokenUsage));

        return result.aiMessage().text();
    }
}

package com.java.tutorial.project.service;

import com.java.coco.utils.date.DateUtil;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author meta
 */
@Slf4j
@Component
@Description("工单相关方法")
public class WorkOrderTool {

    @Tool(value = "生成工单编号")
    public String generateWorkOrderCode(@P("工单类型") String workOrderType) {
        Date date = new Date();
        String time = String.valueOf(date.getTime());
        String workOrder = "W" + DateUtil.format(date, "yyyyMMddHHmmss") + time.substring(
            time.length() - 6) + "-" + workOrderType.hashCode();
        log.info("生成工单编号：{}", workOrder);
        return workOrder;
    }

}

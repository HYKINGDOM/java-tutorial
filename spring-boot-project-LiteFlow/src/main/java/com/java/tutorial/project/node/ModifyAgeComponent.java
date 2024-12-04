package com.java.tutorial.project.node;

import com.alibaba.fastjson2.JSON;
import com.java.tutorial.project.domain.EmployeeInfo;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author meta
 */
@Slf4j
@LiteflowComponent("modifyAge")
public class ModifyAgeComponent extends NodeComponent {
    @Override
    public void process() {
        EmployeeInfo employeeInfo = this.getCurrLoopObj();
        log.info("employeeInfo:{}", JSON.toJSONString(employeeInfo));
    }
}

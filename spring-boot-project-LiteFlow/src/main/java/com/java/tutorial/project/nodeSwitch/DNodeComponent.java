package com.java.tutorial.project.nodeSwitch;

import com.alibaba.fastjson2.JSON;
import com.java.tutorial.project.domain.EmployeeInfo;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@LiteflowComponent("dNode")
public class DNodeComponent extends NodeComponent {


    @Override
    public void process() throws Exception {
        EmployeeInfo employeeInfo = this.getCurrLoopObj();
        log.info("dNode:{}", JSON.toJSONString(employeeInfo));
    }
}

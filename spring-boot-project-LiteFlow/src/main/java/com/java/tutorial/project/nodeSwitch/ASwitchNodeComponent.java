package com.java.tutorial.project.nodeSwitch;

import com.java.tutorial.project.domain.EmployeeInfo;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@LiteflowComponent("aNode")
public class ASwitchNodeComponent extends NodeSwitchComponent {
    @Override
    public String processSwitch() {
        EmployeeInfo employeeInfo = this.getCurrLoopObj();

        Integer age = employeeInfo.getAge();
        if (age > 35) {
            return "bNode";
        }else if (age < 28){
            return "cNode";
        }else {
            return "dNode";
        }
    }
}

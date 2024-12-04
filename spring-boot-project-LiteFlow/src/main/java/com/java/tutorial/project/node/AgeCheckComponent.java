package com.java.tutorial.project.node;

import com.java.tutorial.project.domain.EmployeeInfo;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeBooleanComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author meta
 */
@Slf4j
@LiteflowComponent("ageCheck")
public class AgeCheckComponent extends NodeBooleanComponent {
    @Override
    public boolean processBoolean() {
        EmployeeInfo employeeInfo = this.getCurrLoopObj();

        Integer age = employeeInfo.getAge();
        if (age > 35) {
            return false;
        }

        return true;
    }
}

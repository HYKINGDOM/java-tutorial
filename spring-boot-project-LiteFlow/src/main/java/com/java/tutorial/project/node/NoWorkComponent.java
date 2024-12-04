package com.java.tutorial.project.node;

import com.java.tutorial.project.domain.EmployeeInfo;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author meta
 */
@Slf4j
@LiteflowComponent("noWork")
public class NoWorkComponent extends NodeComponent {
    @Override
    public void process() {
        EmployeeInfo employeeInfo = this.getCurrLoopObj();
        log.info("抱歉:{}, 你的年龄{}不在我们的筛选范围", employeeInfo.getName(), employeeInfo.getAge());
    }
}

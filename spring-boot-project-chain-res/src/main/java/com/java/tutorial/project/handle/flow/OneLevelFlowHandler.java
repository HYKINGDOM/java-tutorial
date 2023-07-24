package com.java.tutorial.project.handle.flow;

import com.java.tutorial.project.config.AbstractFlowHandler;
import com.java.tutorial.project.domian.MoneyPayVO;
import org.springframework.stereotype.Component;

@Component
public class OneLevelFlowHandler extends AbstractFlowHandler {
    @Override
    public boolean approve(MoneyPayVO param) {
        return false;
    }
}

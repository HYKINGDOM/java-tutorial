package com.java.tutorial.project.factory;

import com.feiniaojin.ddd.ecosystem.pie.OutboundFactory;
import com.java.tutorial.project.domain.HandlerResult;

/**
 * 出参工厂
 *
 * @author meta
 */
public class HandlerResultFactory implements OutboundFactory {

    @Override
    public Object newInstance() {
        HandlerResult handlerResult = new HandlerResult();
        handlerResult.setCode(0);
        handlerResult.setMsg("ok");
        return handlerResult;
    }
}

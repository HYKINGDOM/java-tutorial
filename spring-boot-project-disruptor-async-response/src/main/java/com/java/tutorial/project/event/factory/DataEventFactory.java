package com.java.tutorial.project.event.factory;

import com.java.tutorial.project.domain.DataEventRequest;
import com.lmax.disruptor.EventFactory;

/**
 * @author kscs
 */
public class DataEventFactory implements EventFactory<DataEventRequest> {
    @Override
    public DataEventRequest newInstance() {
        return new DataEventRequest();
    }
}
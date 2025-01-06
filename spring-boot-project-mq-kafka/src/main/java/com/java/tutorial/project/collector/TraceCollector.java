package com.java.tutorial.project.collector;

import com.java.tutorial.project.domain.TraceEvent;

/**
 * @author meta
 */
public interface TraceCollector {


    void collect(TraceEvent event);
}

package com.java.tutorial.project.collector.impl;

import com.java.tutorial.project.collector.TraceCollector;
import com.java.tutorial.project.domain.TraceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author meta
 */
@Slf4j
@Service
public class TraceCollectorImpl implements TraceCollector {
    @Override
    public void collect(TraceEvent event) {

    }
}

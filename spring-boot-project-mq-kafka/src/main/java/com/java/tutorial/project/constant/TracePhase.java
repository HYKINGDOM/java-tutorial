package com.java.tutorial.project.constant;

public interface TracePhase {


    String CONSUMER_COMMIT = "CONSUMER_COMMIT";

    String CONSUMER_RECEIVE = "CONSUMER_RECEIVE";

    String PRODUCER_ACK = "PRODUCER_ACK";

    String PRODUCER_SEND = "PRODUCER_SEND";
    
    String STREAMS_PROCESS_START = "STREAMS_PROCESS_START";
    
    String STREAMS_PROCESS_END = "STREAMS_PROCESS_END";
}

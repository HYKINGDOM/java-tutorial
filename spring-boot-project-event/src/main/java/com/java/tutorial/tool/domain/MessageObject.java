//package com.java.tutorial.tool.domain;
//
//
//import com.java.tutorial.tool.util.TraceIDUtil;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//
//
///**
// * @author HY
// */
//public class MessageObject {
//
//    private Long businessId;
//
//    private String traceId;
//
//    private Object payload;
//
//    private String version;
//
//    private LocalDateTime currentDateTime;
//
//
//    public MessageObject(Builder builder) {
//        this.businessId = builder.businessId;
//        this.traceId = builder.traceId;
//        this.payload = builder.payload;
//        this.version = builder.version;
//        this.currentDateTime = builder.currentDateTime;
//    }
//
//    @lombok.Builder
//    public static class Builder {
//
//        private Long businessId;
//
//        private String traceId;
//
//        private Object payload;
//
//        private String version;
//
//        private LocalDateTime currentDateTime;
//
//        public Builder businessId(Long businessId) {
//            this.businessId = businessId;
//            return this;
//        }
//
//        public Builder payload(Object payload) {
//            this.payload = payload;
//            return this;
//        }
//
//        public Builder version(String version) {
//            this.version = version;
//            return this;
//        }
//
//
//        public MessageObject build() {
//            this.traceId = TraceIDUtil.getTraceId();
//            this.currentDateTime = LocalDateTime.now();
//            return new MessageObject(this);
//        }
//    }
//}

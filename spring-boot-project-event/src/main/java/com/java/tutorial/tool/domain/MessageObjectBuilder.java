//package com.java.tutorial.tool.domain;
//
//import cn.hutool.core.lang.Assert;
//import com.alibaba.fastjson2.JSONObject;
//import com.java.tutorial.tool.util.TraceIDUtil;
//import lombok.Builder;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//
///**
// * @author HY
// */
//public class MessageObjectBuilder {
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
////    private MessageObjectBuilder(MessageObject providedMessage) {
////        Assert.notNull(providedMessage, "providedMessage must not be null");
////        MessageObjectBuilder.providedMessage = providedMessage;
////    }
//
//    public static MessageObjectBuilder setDataMessage(Object dataMessage) {
//        this.payload = dataMessage;
//        return this;
//    }
//
//    public MessageObjectBuilder setBusinessId(Long businessId) {
//        this.businessId = businessId;
//        return this;
//    }
//
//
//    /**
//     * 消费生产者在构建消息体时，默认将当前的TraceID set到消息体中
//     **/
//    public MessageObject build() {
//        this.traceId = TraceIDUtil.getTraceId();
//        this.currentDateTime = LocalDateTime.now();
//        return new MessageObject(this);
//    }
//
//
//    /**
//     * 消费者端在转换消息体对象的时候自动取出TraceID 进行Set
//     **/
////    public static MessageObject parseDataMessage(Object dataMessage) {
////        MessageObject messageObject1 = JSONObject.parseObject(String.valueOf(dataMessage), MessageObject.class);
////        MessageObject messageObject = MessageObject.builder().payload(dataMessage).build();
////        TraceIDUtil.setTraceIdToMdcAndTtl(messageObject.getTraceId());
////        return messageObject;
////    }
//
//
//    @Getter
//    @Setter
//    private static class MessageObject {
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
//
//        public MessageObject(MessageObjectBuilder messageObjectBuilder) {
//            this.businessId = messageObjectBuilder.businessId;
//            this.traceId = messageObjectBuilder.traceId;
//            this.payload = messageObjectBuilder.payload;
//            this.version = messageObjectBuilder.version;
//            this.currentDateTime = messageObjectBuilder.currentDateTime;
//        }
//    }
//}
//

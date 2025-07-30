//package com.java.tutorial.project.config;
//
//import com.mybatisflex.core.audit.AuditMessage;
//import com.mybatisflex.core.audit.MessageFactory;
//
//public class MybatisFlexAuditMessageFactory implements MessageFactory {
//
//    @Override
//    public AuditMessage create() {
//        AuditMessage message = new AuditMessage();
//
//        // 在这里
//        // 设置 message 的基础内容，包括 platform、module、url、user、userIp、hostIp 内容
//        // 剩下的 query、queryParams、queryCount、queryTime、elapsedTime 为 mybatis-flex 设置
//
//        message.setUser(SecurityUtils.getDefaultLoginUserName());
//
//        return message;
//    }
//}

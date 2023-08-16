//package com.java.tutorial.project.config;
//
//
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ConditionalOnClass(MongoClient.class)
//@EnableConfigurationProperties(MongoOptionProperties.class)
//@ConditionalOnMissingBean(type = "org.springframework.data.mongodb.MongoDbFactory")
//public class MongoPlusAutoConfiguration {
//
//    @Bean
//    public MongoClientSettings mongoClientOptions(MongoOptionProperties mongoOptionProperties) {
//        if (mongoOptionProperties == null) {
//            return new MongoClientSettings.Builder().build();
//        }
//
//        return new MongoClientOptions.Builder()
//                .minConnectionsPerHost(mongoOptionProperties.getMinConnectionPerHost())
//                .connectionsPerHost(mongoOptionProperties.getMaxConnectionPerHost())
//                .threadsAllowedToBlockForConnectionMultiplier(mongoOptionProperties.getThreadsAllowedToBlockForConnectionMultiplier())
//                .serverSelectionTimeout(mongoOptionProperties.getServerSelectionTimeout())
//                .maxWaitTime(mongoOptionProperties.getMaxWaitTime())
//                .maxConnectionIdleTime(mongoOptionProperties.getMaxConnectionIdleTime())
//                .maxConnectionLifeTime(mongoOptionProperties.getMaxConnectionLifeTime())
//                .connectTimeout(mongoOptionProperties.getConnectTimeout())
//                .socketTimeout(mongoOptionProperties.getSocketTimeout())
//                .socketKeepAlive(mongoOptionProperties.getSocketKeepAlive())
//                .sslEnabled(mongoOptionProperties.getSslEnabled())
//                .sslInvalidHostNameAllowed(mongoOptionProperties.getSslInvalidHostNameAllowed())
//                .alwaysUseMBeans(mongoOptionProperties.getAlwaysUseMBeans())
//                .heartbeatFrequency(mongoOptionProperties.getHeartbeatFrequency())
//                .minConnectionsPerHost(mongoOptionProperties.getMinConnectionPerHost())
//                .heartbeatConnectTimeout(mongoOptionProperties.getHeartbeatConnectTimeout())
//                .heartbeatSocketTimeout(mongoOptionProperties.getSocketTimeout())
//                .localThreshold(mongoOptionProperties.getLocalThreshold())
//                .build();
//    }
//
//}
//

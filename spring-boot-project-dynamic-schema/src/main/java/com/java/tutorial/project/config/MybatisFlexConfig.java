package com.java.tutorial.project.config;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.audit.AuditManager;
import com.mybatisflex.core.mybatis.FlexConfiguration;
import com.mybatisflex.core.table.DynamicSchemaProcessor;
import com.mybatisflex.core.table.TableManager;
import com.mybatisflex.spring.boot.ConfigurationCustomizer;
import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisFlexConfig  implements ConfigurationCustomizer, MyBatisFlexCustomizer {

    private static final Logger logger = LoggerFactory.getLogger("mybatis-flex-sql");



    public MybatisFlexConfig() {
        //开启审计功能
        AuditManager.setAuditEnable(true);
        //设置审计工厂创建审计消息
        //        AuditManager.setMessageFactory(new MybatisFlexAuditMessageFactory());
        //设置 自定义 SQL 审计收集器
        AuditManager.setMessageCollector(auditMessage ->
            logger.info("{},{}ms", auditMessage.getFullSql()
                , auditMessage.getElapsedTime())
        );
    }

//    @Bean
//    public DynamicSchemaProcessor dynamicSchemaProcessor(){
//
//        TableManager.setDynamicSchemaProcessor(new DynamicSchemaProcessor() {
//            @Override
//            public String process(String schema, String table) {
//                return schema + "_01";
//            }
//        });
//
//        DynamicSchemaProcessor processor = (schema, table) -> null;
//        return processor;
//    }

    @Override
    public void customize(FlexConfiguration configuration) {
        //mybatis实现的打印sql到控制台，便于调试
        configuration.setLogImpl(StdOutImpl.class);
    }

    @Override
    public void customize(FlexGlobalConfig globalConfig) {

    }
}

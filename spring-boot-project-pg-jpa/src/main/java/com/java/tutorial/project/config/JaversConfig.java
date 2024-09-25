package com.java.tutorial.project.config;

import org.javers.core.json.BasicStringTypeAdapter;
import org.javers.core.json.JsonTypeAdapter;
import org.javers.repository.api.JaversRepository;
import org.javers.repository.sql.DialectName;
import org.javers.repository.sql.SqlRepositoryBuilder;
import org.javers.spring.auditable.AuthorProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

import static org.springframework.jdbc.datasource.DataSourceUtils.getConnection;

@Configuration
public class JaversConfig {
    //    @Bean
    //    public JaversSpringDataAuditable javersSpringDataAuditable() {
    //        return new JaversSpringDataAuditable();
    //    }

    public static class DummyBigDecimalEntity {
        BigDecimal value;

        DummyBigDecimalEntity(BigDecimal value) {
            this.value = value;
        }
    }


    @Bean
    public AuthorProvider authorProvider() {
        return new JaversAuthorProvider();
    }


    @Bean
    public JsonTypeAdapter dummyEntityJsonTypeAdapter() {

        return new BasicStringTypeAdapter<DummyBigDecimalEntity>() {
            @Override
            public String serialize(DummyBigDecimalEntity sourceValue) {
                return sourceValue.value.toString();
            }

            @Override
            public DummyBigDecimalEntity deserialize(String serializedValue) {
                return new DummyBigDecimalEntity(new BigDecimal(serializedValue));
            }

            @Override
            public Class getValueType() {
                return DummyBigDecimalEntity.class;
            }
        };
    }

}

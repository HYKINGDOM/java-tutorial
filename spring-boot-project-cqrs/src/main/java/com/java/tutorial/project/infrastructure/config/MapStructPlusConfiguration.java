package com.java.tutorial.project.infrastructure.config;

import org.springframework.stereotype.Component;

import io.github.linpeilie.annotations.ComponentModelConfig;
import io.github.linpeilie.annotations.MapperConfig;

/**
 * mapstruct-plus配置信息
 */
@Component
@ComponentModelConfig
@MapperConfig(mapperPackage = "com.sdnc.trade.application.assembler",
    adapterPackage = "com.sdnc.trade.application.adapter", adapterClassName = "TradeHallConvertAdapter",
    mapAdapterClassName = "TradeHallMapConvertAdapter")
public class MapStructPlusConfiguration {

}

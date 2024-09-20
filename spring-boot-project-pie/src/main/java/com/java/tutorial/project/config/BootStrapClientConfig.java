package com.java.tutorial.project.config;

import com.feiniaojin.ddd.ecosystem.pie.BootStrap;
import com.java.tutorial.project.factory.ArticleModifyChannel;
import com.java.tutorial.project.factory.HandlerResultFactory;
import com.java.tutorial.project.handlers.ArticleModifyContentHandler;
import com.java.tutorial.project.handlers.ArticleModifyTitleHandler;
import com.java.tutorial.project.handlers.CheckParameterHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BootStrapClientConfig {


    @Bean
    public BootStrap totalHandlerResult() {
        BootStrap bootStrap = new BootStrap();
        //出参工厂
        //自定义channel
        return bootStrap
            //出参工厂
            .outboundFactory(new HandlerResultFactory())
            //自定义channel
            .channel(new ArticleModifyChannel())
            .addChannelHandlerAtLast("checkParameter", new CheckParameterHandler())
            .addChannelHandlerAtLast("modifyTitle", new ArticleModifyTitleHandler())
            .addChannelHandlerAtLast("modifyContent", new ArticleModifyContentHandler());
    }

}

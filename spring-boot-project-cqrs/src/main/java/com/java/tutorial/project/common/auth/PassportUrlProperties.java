package com.java.tutorial.project.common.auth;

import java.util.List;
import java.util.Objects;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 不校验Token的请求
 */
@Getter
@ConfigurationProperties(prefix = "passport.url")
public final class PassportUrlProperties {

    private List<String> ignore;

    public void setIgnore(List<String> ignore) {
        this.ignore = ignore;
    }

    public boolean contains(String path) {
        return Objects.nonNull(this.ignore) && this.ignore.contains(path);
    }

}

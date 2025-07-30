package com.java.tutorial.project.util;


import lombok.extern.slf4j.Slf4j;

/**
 * @author meta
 */
@Slf4j
public class ApiKeyEnvUtil {


    /**
     * 获取环境变量
     * @param getenv
     * @return
     */
    public static String getEnvApiString(String getenv) {
        log.info("get env from:{}", getenv);
        if (!getenv.contains("sk-")) {
            getenv = "sk-" + getenv;
        }
        log.info("get env end:{}", getenv);
        return getenv;
    }


    /**
     * 获取环境变量 api key
     * @param envName
     * @return
     */
    public static String getEnvApiKeyByEnvName(String envName) {

        String envApiKey = System.getenv(envName);
        log.info("get env api key from:{}", envApiKey);
        if (!envApiKey.contains("sk-")) {
            envApiKey = "sk-" + envApiKey;
        }
        log.info("get env end:{}", envApiKey);
        return envApiKey;
    }
}

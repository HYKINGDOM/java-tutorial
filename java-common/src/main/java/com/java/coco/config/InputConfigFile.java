package com.java.coco.config;

import lombok.extern.java.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author meta
 */
@Log
public class InputConfigFile {

    private Properties props;

    private void loadProps() {
        log.info("start loading config.properties file... ...");
        props = new Properties();
        InputStream stream = null;
        String configName = "/config.properties";
        try {
            stream = this.getClass().getResourceAsStream(configName);
            props.load(stream);
        } catch (FileNotFoundException e) {
            log.info("config.properties file not found");
        } catch (IOException e) {
            log.info(e.getMessage());
        } finally {
            try {
                if (null != stream) {
                    stream.close();
                }
            } catch (IOException e) {
                log.info("config.properties file io stream close exception");
            }
        }
        log.info("loading config.properties file success...........properties文件内容：" + props);
    }

    /**
     * get value from Property
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }
}

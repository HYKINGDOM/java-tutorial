package com.java.tutorial.project.template;//package com.woniu.design;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂设计模式
 */
public class Factory {
    private static Map<String, Handler> strategyMap = new HashMap<>();




    public static Handler getInvokeStrategy(String str) {
        return strategyMap.get(str);
    }

    public static void register(String str, Handler handler) {
        if (StringUtils.isEmpty(str) || null == handler) {
            return;
        }
        strategyMap.put(str, handler);
    }


}

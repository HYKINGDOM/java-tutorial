package com.java.tutorial.project.common.kits;

import com.java.tutorial.project.common.exception.SystemException;
import org.springframework.util.Assert;

/**
 * 断言工具类
 */
public final class ValidKit extends Assert {

    /**
     * 不为空
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new SystemException(message);
        }
    }

}

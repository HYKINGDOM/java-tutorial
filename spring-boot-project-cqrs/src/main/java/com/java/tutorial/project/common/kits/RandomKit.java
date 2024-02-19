package com.java.tutorial.project.common.kits;

/**
 * 生成6位随机数
 */
public final class RandomKit {

    public static int next() {
        return (int)((Math.random() * 9 + 1) * 100000);
    }

}

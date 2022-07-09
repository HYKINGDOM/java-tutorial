package com.java.coco.utils;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomUtils {


    public static Integer generateRandomInteger() {
        return ThreadLocalRandom.current().nextInt(100000, 999999);
    }
}

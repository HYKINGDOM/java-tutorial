package com.java.coco.exception;

public class FileResource implements AutoCloseable {
    public void read() {
        System.out.println("获取资源");
    }

    @Override
    public void close() throws Exception {
        System.out.println("关闭资源");
    }

}

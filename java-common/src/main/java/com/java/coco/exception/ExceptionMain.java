package com.java.coco.exception;

public class ExceptionMain {

    public static void main(String[] args) {
        try (FileResource fileResource = new FileResource()) {
            fileResource.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.java.coco.config;

public class GetConfigFile {

    public static void main(String[] args) {
        InputConfigFile inputConfigFile = new InputConfigFile();

        System.out.println(inputConfigFile.getProperty("username"));
    }
}

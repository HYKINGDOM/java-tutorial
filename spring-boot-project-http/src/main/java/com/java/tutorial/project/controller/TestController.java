package com.java.tutorial.project.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author HY
 */
@RestController
public class TestController {

    @PostMapping(value = "/upload")
    public Long updateUser(@RequestParam Long userId, @RequestParam("img") MultipartFile img) throws IOException {
        System.out.println("userId:" + userId + " upload img :" + img.getName());
        img.transferTo(new File("D://1.jpg"));
        return img.getSize();
    }

}

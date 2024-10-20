package com.java.tutorial.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author meta
 */
@RestController
public class TestController {

    @PostMapping(value = "/upload")
    public Long updateUser(@RequestParam Long userId, @RequestParam("img") MultipartFile img) throws IOException {
        System.out.println("userId:" + userId + " upload img :" + img.getName());
        img.transferTo(new File("D://1.jpg"));
        return img.getSize();
    }

    @GetMapping(value = "/user/{id}")
    public Map<String, Object> user(@PathVariable Long id, @RequestParam String userName,
        @RequestHeader String userHeader, HttpServletResponse response, @CookieValue("JSESSIONID") String cookie) {
        response.addHeader("X-USER-ID", id.toString());
        response.addHeader("cookie", cookie);
        return Map.of("userName", userName, "id", id, "userHeader", userHeader);
    }

    @PostMapping(value = "/user")
    public User addUser(User user) {
        System.out.println(user.getName() + ":" + user.getPassword());
        return user;
    }

    @PostMapping(value = "/user/update")
    public User updateUser(@RequestBody User user) {
        System.out.println(user.getName() + ":" + user.getPassword());
        return user;
    }

}

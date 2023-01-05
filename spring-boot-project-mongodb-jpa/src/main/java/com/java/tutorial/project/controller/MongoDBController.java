package com.java.tutorial.project.controller;


import com.java.tutorial.project.domain.User;
import com.java.tutorial.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.executable.ValidateOnExecution;

@RestController
@RequestMapping("/mongo")
public class MongoDBController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void testMongo() {
        User user = new User();
        user.setId(200000L);
        user.setUserName("秋田");
        user.setPassWord("weadawdawdaw456451@@@");
        userService.saveUser(user);
    }
    @GetMapping
    public void getUser(){
        userService.findUserByUserName("User.builder().build()");
    }
}

package com.java.tutorial.project.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bingbing
 */
@RestController
@RequestMapping("user")
public class UserController {

    @PostAuthorize("returnObject.username == authentication.principal.nickName")
    public String getUserInfo() {

        return "";
    }

    @PreAuthorize("#userId == authentication.principal.userId or hasAuthority(‘ADMIN’)")
    public String getUserInfoAuth() {

        return "";
    }
}

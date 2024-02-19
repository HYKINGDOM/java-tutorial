package com.java.tutorial.project.controller;

import com.java.tutorial.project.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hy
 */
@RestController
@RequestMapping("/user")
public class SysUserInfoController {

    @GetMapping("/user")
    public Result getUserInfo() {

        return Result.ok();
    }
}

package com.java.tutorial.project.controller;

import com.java.tutorial.project.service.DagServiceOne;
import com.java.tutorial.project.service.DagServiceTwo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DagController {


    @Resource
    private DagServiceOne dagServiceOne;

    @Resource
    private DagServiceTwo dagServiceTwo;


    @GetMapping("/dagTest")
    public void dagTest() {
        String dagTest = dagServiceOne.handle("dagTest");
        String dagTest1 = dagServiceTwo.handle("dagTest");

        System.out.println(dagTest + " " + dagTest1);
    }

}

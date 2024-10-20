package com.java.tutorial.project.service;

import org.springframework.stereotype.Service;

@Service
public class DagServiceTwo {

    public String handle(String param) {

        return "DagServiceTwo"+ param;
    }
}

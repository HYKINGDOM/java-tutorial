package com.java.tutorial.project.service;

import org.springframework.stereotype.Service;

@Service
public class DagServiceOne {


    public String handle(String param) {

        return "DagServiceOne"+ param;
    }
}

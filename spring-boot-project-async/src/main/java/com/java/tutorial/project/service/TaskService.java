package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.User;

import java.util.List;

public interface TaskService {



    List<String> combindStrList(String str);


    List<String> serialStrList(String str);


    List<String> taskStringList01(String str);


    List<String> taskStringList02(Long ids);


    List<String> taskStringList03(Integer num);


    List<String> taskStringList04(String str);


    User taskCreateUser01(String str);

    User taskCreateUser03(String str);

    User taskCreateUser04(String str);
}

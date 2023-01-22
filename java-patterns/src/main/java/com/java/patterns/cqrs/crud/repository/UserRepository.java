package com.java.patterns.cqrs.crud.repository;


import com.java.patterns.cqrs.domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private Map<String, User> store = new HashMap<>();

    public void addUser(String id, User user) {
        store.put(id, user);
    }

    public User getUser(String id) {
        return store.get(id);
    }

}

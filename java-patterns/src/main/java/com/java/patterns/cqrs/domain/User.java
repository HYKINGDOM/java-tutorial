package com.java.patterns.cqrs.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
public class User {
    @NonNull
    private String userid;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    private Set<Contact> contacts = new HashSet<>();
    private Set<Address> addresses = new HashSet<>();

}

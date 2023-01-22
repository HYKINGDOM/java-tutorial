package com.java.patterns.cqrs.cqrs.commands;


import com.java.patterns.cqrs.domain.Address;
import com.java.patterns.cqrs.domain.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class UpdateUserCommand {

    private String userId;
    private Set<Address> addresses = new HashSet<>();
    private Set<Contact> contacts = new HashSet<>();

}

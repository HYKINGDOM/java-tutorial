package com.java.patterns.cqrs.es.service;

import com.java.patterns.cqrs.domain.Address;
import com.java.patterns.cqrs.domain.Contact;
import com.java.patterns.cqrs.domain.User;
import com.java.patterns.cqrs.es.events.UserAddressAddedEvent;
import com.java.patterns.cqrs.es.events.UserAddressRemovedEvent;
import com.java.patterns.cqrs.es.events.UserContactAddedEvent;
import com.java.patterns.cqrs.es.events.UserContactRemovedEvent;
import com.java.patterns.cqrs.es.events.UserCreatedEvent;
import com.java.patterns.cqrs.es.repository.EventStore;

import java.util.Set;
import java.util.stream.Collectors;

public class UserService {

    private EventStore repository;

    public UserService(EventStore repository) {
        this.repository = repository;
    }

    public void createUser(String userId, String firstName, String lastName) {
        repository.addEvent(userId, new UserCreatedEvent(userId, firstName, lastName));
    }

    public void updateUser(String userId, Set<Contact> contacts, Set<Address> addresses) throws Exception {
        User user = UserUtility.recreateUserState(repository, userId);
        if (user == null) {
            throw new Exception("User does not exist.");
        }

        user.getContacts().stream().filter(c -> !contacts.contains(c))
            .forEach(c -> repository.addEvent(userId, new UserContactRemovedEvent(c.getType(), c.getDetail())));
        contacts.stream().filter(c -> !user.getContacts().contains(c))
            .forEach(c -> repository.addEvent(userId, new UserContactAddedEvent(c.getType(), c.getDetail())));
        user.getAddresses().stream().filter(a -> !addresses.contains(a)).forEach(
            a -> repository.addEvent(userId, new UserAddressRemovedEvent(a.getCity(), a.getState(), a.getPostcode())));
        addresses.stream().filter(a -> !user.getAddresses().contains(a)).forEach(
            a -> repository.addEvent(userId, new UserAddressAddedEvent(a.getCity(), a.getState(), a.getPostcode())));
    }

    public Set<Contact> getContactByType(String userId, String contactType) throws Exception {
        User user = UserUtility.recreateUserState(repository, userId);
        if (user == null)
            throw new Exception("User does not exist.");
        return user.getContacts().stream().filter(c -> c.getType().equals(contactType)).collect(Collectors.toSet());
    }

    public Set<Address> getAddressByRegion(String userId, String state) throws Exception {
        User user = UserUtility.recreateUserState(repository, userId);
        if (user == null)
            throw new Exception("User does not exist.");
        return user.getAddresses().stream().filter(a -> a.getState().equals(state)).collect(Collectors.toSet());
    }
}

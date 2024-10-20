package com.java.patterns.cqrs.cqrs.projections;

import com.java.patterns.cqrs.cqrs.queries.AddressByRegionQuery;
import com.java.patterns.cqrs.cqrs.queries.ContactByTypeQuery;
import com.java.patterns.cqrs.cqrs.repository.UserReadRepository;
import com.java.patterns.cqrs.domain.Address;
import com.java.patterns.cqrs.domain.Contact;
import com.java.patterns.cqrs.domain.UserAddress;
import com.java.patterns.cqrs.domain.UserContact;

import java.util.Set;

/**
 * 用户规划
 *
 * @author meta
 */
public class UserProjection {

    private UserReadRepository repository;

    public UserProjection(UserReadRepository repository) {
        this.repository = repository;
    }

    public Set<Contact> handle(ContactByTypeQuery query) throws Exception {
        UserContact userContact = repository.getUserContact(query.getUserId());
        if (userContact == null) {
            throw new Exception("User does not exist.");
        }
        return userContact.getContactByType().get(query.getContactType());
    }

    public Set<Address> handle(AddressByRegionQuery query) throws Exception {
        UserAddress userAddress = repository.getUserAddress(query.getUserId());
        if (userAddress == null) {
            throw new Exception("User does not exist.");
        }
        return userAddress.getAddressByRegion().get(query.getState());
    }

}

package com.java.patterns.cqrs.cqrs.escqrs;



import com.java.patterns.cqrs.cqrs.commands.CreateUserCommand;
import com.java.patterns.cqrs.cqrs.commands.UpdateUserCommand;
import com.java.patterns.cqrs.cqrs.projections.UserProjection;
import com.java.patterns.cqrs.cqrs.queries.AddressByRegionQuery;
import com.java.patterns.cqrs.cqrs.queries.ContactByTypeQuery;
import com.java.patterns.cqrs.cqrs.repository.UserReadRepository;
import com.java.patterns.cqrs.domain.Address;
import com.java.patterns.cqrs.domain.Contact;
import com.java.patterns.cqrs.es.events.Event;
import com.java.patterns.cqrs.es.repository.EventStore;
import com.java.patterns.cqrs.escqrs.aggregates.UserAggregate;
import com.java.patterns.cqrs.escqrs.projectors.UserProjector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationUnitTest {

    private EventStore writeRepository;
    private UserReadRepository readRepository;
    private UserProjector projector;
    private UserAggregate userAggregate;
    private UserProjection userProjection;

    @BeforeEach
    public void setUp() {
        writeRepository = new EventStore();
        readRepository = new UserReadRepository();
        projector = new UserProjector(readRepository);
        userAggregate = new UserAggregate(writeRepository);
        userProjection = new UserProjection(readRepository);
    }

    @Test
    public void givenCQRSApplication_whenCommandRun_thenQueryShouldReturnResult() throws Exception {
        String userId = UUID.randomUUID()
                .toString();
        List<Event> events = null;
        CreateUserCommand createUserCommand = new CreateUserCommand(userId, "Kumar", "Chandrakant");
        events = userAggregate.handleCreateUserCommand(createUserCommand);

        projector.project(userId, events);

        UpdateUserCommand updateUserCommand = new UpdateUserCommand(userId, Stream.of(new Address("New York", "NY", "10001"), new Address("Los Angeles", "CA", "90001"))
                .collect(Collectors.toSet()),
                Stream.of(new Contact("EMAIL", "tom.sawyer@gmail.com"), new Contact("EMAIL", "tom.sawyer@rediff.com"))
                        .collect(Collectors.toSet()));
        events = userAggregate.handleUpdateUserCommand(updateUserCommand);
        projector.project(userId, events);

        updateUserCommand = new UpdateUserCommand(userId, Stream.of(new Address("New York", "NY", "10001"), new Address("Housten", "TX", "77001"))
                .collect(Collectors.toSet()),
                Stream.of(new Contact("EMAIL", "tom.sawyer@gmail.com"), new Contact("PHONE", "700-000-0001"))
                        .collect(Collectors.toSet()));
        events = userAggregate.handleUpdateUserCommand(updateUserCommand);
        projector.project(userId, events);

        ContactByTypeQuery contactByTypeQuery = new ContactByTypeQuery(userId, "EMAIL");
        assertEquals(Stream.of(new Contact("EMAIL", "tom.sawyer@gmail.com"))
                .collect(Collectors.toSet()), userProjection.handle(contactByTypeQuery));
        AddressByRegionQuery addressByRegionQuery = new AddressByRegionQuery(userId, "NY");
        assertEquals(Stream.of(new Address("New York", "NY", "10001"))
                .collect(Collectors.toSet()), userProjection.handle(addressByRegionQuery));

    }

}

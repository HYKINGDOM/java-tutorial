package com.java.patterns.cqrs.es.events;

import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@ToString
public abstract class Event {

    public final UUID id = UUID.randomUUID();

    public final Date created = new Date();

}

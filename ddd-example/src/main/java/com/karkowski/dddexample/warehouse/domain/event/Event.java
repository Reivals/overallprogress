package com.karkowski.dddexample.warehouse.domain.event;

import java.util.Date;
import java.util.UUID;

public abstract class Event {
    public final UUID id = UUID.randomUUID();
    public final Date created = new Date();

    public abstract String getEvent();
    public abstract String getEventName();


}

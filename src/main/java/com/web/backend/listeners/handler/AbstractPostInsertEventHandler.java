package com.web.backend.listeners.handler;

import org.hibernate.event.spi.*;

public abstract class AbstractPostInsertEventHandler implements PostInsertEventHandler {
    protected PostInsertEvent event;

    @Override
    public final void register(PostInsertEvent ev) {
        this.event = ev;
    }
}

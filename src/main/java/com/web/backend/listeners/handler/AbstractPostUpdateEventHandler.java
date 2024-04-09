package com.web.backend.listeners.handler;

import org.hibernate.event.spi.*;

public abstract class AbstractPostUpdateEventHandler implements PostUpdateEventHandler {
    protected PostUpdateEvent event;

    @Override
    public final void register(PostUpdateEvent ev) {
        this.event = ev;
    }
}

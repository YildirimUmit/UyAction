package com.web.backend.listeners.handler;

import org.hibernate.event.spi.*;

public interface PostUpdateEventHandler extends Runnable {
    void register(PostUpdateEvent event);
}

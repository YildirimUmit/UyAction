package com.web.backend.listeners.handler;

import org.hibernate.event.spi.*;

public interface PostInsertEventHandler extends Runnable {
    void register(PostInsertEvent event);
}

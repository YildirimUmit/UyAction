package com.web.backend.listeners.listener;


import com.web.backend.listeners.handler.*;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class InsertListener implements PostInsertEventListener {

    private final List<PostInsertEventHandler> handlers;
    private final ExecutorServiceFactory factory;

    @Autowired
    public InsertListener(List<PostInsertEventHandler> handlers, ExecutorServiceFactory factory) {
        this.handlers = handlers;
        this.factory = factory;
    }

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        for (PostInsertEventHandler han : handlers) {
            han.register(postInsertEvent);
            this.factory.getExecutorService().execute(han);
        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }
}

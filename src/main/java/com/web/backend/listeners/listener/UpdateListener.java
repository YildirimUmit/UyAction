package com.web.backend.listeners.listener;


import com.web.backend.listeners.handler.*;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class UpdateListener implements PostUpdateEventListener {

    private final List<PostUpdateEventHandler> handlers;
    private final ExecutorServiceFactory factory;

    @Autowired
    public UpdateListener(List<PostUpdateEventHandler> handlers, ExecutorServiceFactory factory) {
        this.handlers = handlers;
        this.factory = factory;
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        for (PostUpdateEventHandler han : handlers) {
            han.register(postUpdateEvent);
            this.factory.getExecutorService().execute(han);
        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }
}
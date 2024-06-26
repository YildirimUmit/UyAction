package com.web.backend.listeners.handler;

import org.springframework.stereotype.*;

@Component
public class LoggingPostInsertEventHandler extends AbstractPostInsertEventHandler {
    @Override
    public void run() {
        System.out.println("------HIBERNATE EVENT------");
        String sb = "New entity " +
                this.event.getPersister().getEntityMetamodel().getName() +
                " with ID " +
                this.event.getId() +
                " was committed by Hibernate.\n";
        System.out.println(sb);
    }
}

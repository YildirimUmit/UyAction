package com.web.backend;


import com.web.backend.listeners.listener.*;
import org.hibernate.event.service.spi.*;
import org.hibernate.event.spi.*;
import org.hibernate.internal.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.annotation.*;
import javax.persistence.*;

@Component
public class HibernateListenerConfigurer {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private final UpdateListener updateListener;
    private final InsertListener insertListener;

    @Autowired
    public HibernateListenerConfigurer(UpdateListener updateListener, InsertListener insertListener) {
        this.updateListener = updateListener;
        this.insertListener = insertListener;
    }

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(updateListener);
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(insertListener);

    }
}

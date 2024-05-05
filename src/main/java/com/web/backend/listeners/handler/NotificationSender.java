package com.web.backend.listeners.handler;



import com.web.backend.listeners.listener.*;
import com.web.backend.model.*;
import com.web.backend.producer.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.annotation.*;
import java.util.*;

@Component
public class NotificationSender {
//    @Autowired
//    RabbitTemplate amqpTemplate;
//
//    @Autowired
//    private NotificationProducer producer;
//
//    @Autowired
//    private NotificationListener notificationListener;
//
//
//    public void getThread() {
//        Thread thread = new Thread(() -> {
//            while (true) {
//                Notification notification = new Notification();
//                notification.setNotificationId(UUID.randomUUID().toString());
//                notification.setCreatedAt(new Date());
//                notification.setMessage("Haydi kodlayalımdan mesaj var...");
//                notification.setSeen(false);
//                producer.sendToQueueMaileSend(notification);
//
//                try {
//                    Thread.sleep(000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.setName("Notification sender");
//        thread.start();
//    }
//
//
//    @PostConstruct
//    public void init() {
//
//        getThread();
//
//    }


}

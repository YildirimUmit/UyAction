package com.web.backend.listeners.listener;



import com.rabbitmq.client.*;
import com.web.backend.model.*;
import com.web.backend.producer.*;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.*;

import java.io.*;

@Service
public class NotificationListener {

    @Autowired
    private NotificationProducer producer;
//    @RabbitListener(queues = "mailesend")
//    public void handleMessageMaileSend(Notification notification, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag )throws IOException {
//        System.out.println("----------------------Maile Send Listener--------------------------");
//        System.out.println("mailesend Message received..");
//        System.out.println(notification.toString());
//        channel.basicAck(tag, true);
//                notification.setMessage("Send Error");
//        notification.setSeen(true);
//        producer.sendToQueueMaileInfo(notification);
//    }



    @RabbitListener(queues = "mailesinfo")
    public void handleMessageMailseInfo(Notification notification,Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag )throws IOException {
        System.out.println("----------------------Maile Info Listener--------------------------");
        System.out.println("maileinfo Message received..");
        System.out.println(notification.toString());
        channel.basicAck(tag, false);

    }

    @RabbitListener(queues = "maileserror")
    public void handleMessageMailseError(Notification notification,Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag )throws IOException {
        System.out.println("----------------------Maile maileserror Listener--------------------------");
        System.out.println("maileserror Message received..");
        System.out.println(notification.toString());
        channel.basicAck(tag, false);

    }
}

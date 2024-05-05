package com.web.backend.producer;



import com.web.backend.model.*;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.annotation.*;
import java.util.*;

@Service
public class NotificationProducer   {  //  implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback  {

    @Value("${sr.rabbit.routing.name.mailesend}")
    private String routingNameMaileSend;

    @Value("${sr.rabbit.queue.name.mailesinfo}")
    private String routingNameMaileInfo;

    @Value("${sr.rabbit.exchange.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {

    }


    public void sendToQueueMaileSend(Notification notification) {
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setReturnCallback(this);

        System.out.println("Notification Sent I  D : " + notification.getNotificationId());

        rabbitTemplate.convertAndSend(exchangeName, routingNameMaileSend, notification);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
                System.out.println("ack: " + ack + ". correlationData: " + correlationData + "cause : " + cause)
        );

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, tmpExchange, tmpRoutingKey) ->
                System.out.println("send message failed: " + message + " " + replyText)
      );
    }


//    public void sendToQueueMaileInfo(Notification notification) {
//
////        rabbitTemplate.setConfirmCallback(this);
////        rabbitTemplate.setReturnCallback(this);
//        System.out.println("Notification Sent ID : " + notification.getNotificationId());
//        rabbitTemplate.convertAndSend(exchangeName, routingNameMaileInfo, notification);
//
//    }

//    @Override
//    public void confirm(CorrelationData correlationData, boolean b, String s) {// message sucesfuly send
////        Optional<CorrelationData> optional=  Optional.ofNullable(correlationData);
////        optional.ifPresent( optCorrelationData ->{
////            System.err.println("---confirm--->"+optCorrelationData.getReturnedMessage().getBody().toString());
////
////        });
////        System.err.println("---confirm--->"+b);
////        System.err.println("---confirm--->"+s);
//
//    }
//
//    @Override
//    public void returnedMessage(Message message, int i, String s, String s1, String s2) {// message don't sucesfuly send
////        System.err.println("---returnedMessage--->"+message);
////        System.err.println("---returnedMessage--->"+message.getMessageProperties().getPriority());
////
////        System.err.println("---returnedMessage--->"+i);
////        System.err.println("---returnedMessage--->"+s);
////
////        System.err.println("---returnedMessage--->"+s1);
////        System.err.println("---returnedMessage--->"+s2);
//    }
}

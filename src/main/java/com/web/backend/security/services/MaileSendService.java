package com.web.backend.security.services;

import com.fasterxml.jackson.databind.*;
import com.web.backend.enums.*;
import com.web.backend.listeners.listener.*;
import com.web.backend.model.*;
import com.web.backend.producer.*;
import lombok.extern.slf4j.*;
import org.modelmapper.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Slf4j
@Service
public class MaileSendService {

    @Autowired
    private NotificationProducer producer;

    @Autowired
    ObjectMapper jsonMaileUser;

    @Value("${uyactionfront.dev.prod}")
    String url;

    public void getSendMaileForgetPassword(String uuid){
        try {
            System.out.println("**********************Maile Send Forget Password*************************");
            MaileUser user=new MaileUser();
            TargetEmail targetEmail=new TargetEmail();
            targetEmail.setEmail("umityild@gmail.com");
            targetEmail.setLastName("Yıldırım");
            targetEmail.setFirstName("Ümit");
            targetEmail.setMessage(url+"/"+uuid);
            user.addEmails(targetEmail);



            Notification notification = new Notification();
            notification.setNotificationId(UUID.randomUUID().toString());
            notification.setCreatedAt(new Date());
            notification.setMessage(jsonMaileUser.writeValueAsString(user));
            notification.setMaileType(MaileType.FORGET_PASSWORD);
            notification.setSeen(false);
            producer.sendToQueueMaileSend(notification);
        }catch (Exception exception) {
            log.debug("Parse exception: {}"+ exception.getMessage());
        }

    }




}

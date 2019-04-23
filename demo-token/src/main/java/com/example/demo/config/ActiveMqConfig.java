package com.example.demo.config;

import org.apache.activemq.command.DestinationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Auther: Coffee
 * @Date: 2019/4/13
 *
 */
//@Component
public class ActiveMqConfig {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(String name, Object msg) {
        DestinationInfo destinationInfo = new DestinationInfo();
        jmsMessagingTemplate.convertAndSend(name,msg);
    }
}

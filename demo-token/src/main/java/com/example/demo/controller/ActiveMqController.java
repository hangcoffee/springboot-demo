package com.example.demo.controller;

import com.example.demo.config.ActiveMqConfig;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;

/**
 * @Auther: Coffee
 * @Date: 2019/4/13
 */
@RestController
public class ActiveMqController {

    @Autowired
    private ActiveMqConfig activeMqConfig;


    @GetMapping("/send")
    public String send() {
        activeMqConfig.sendMsg("qg:test", "hello,world!");
        return "success";
    }

    @JmsListener(destination = "qg:test")
    public void receiveMsg(Object msg) throws JMSException {
        ActiveMQTextMessage textMessage = (ActiveMQTextMessage) msg;
        System.out.println(textMessage.getText());
    }
}

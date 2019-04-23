package com.example.demo.controller;

import com.example.demo.config.KafkaConfig;
import com.example.demo.constant.KafkaConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Coffee
 * @Date: 2019/4/9
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaConfig kafkaConfig;

    @GetMapping("/sendMsg")
    public String sendMsg() {
       return kafkaConfig.sendMsg(KafkaConstant.KAFKA_TOPIC, "", "hello,kafka");
    }
}

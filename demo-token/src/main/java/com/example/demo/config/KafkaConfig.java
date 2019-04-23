package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.example.demo.constant.KafkaConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: Coffee
 * @Date: 2019/4/9
 */
@Slf4j
@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * 发送消息
     * @param topic
     * @param key
     * @param msg
     * @return
     */
    public String sendMsg(String topic, String key, String msg) {
        try {
            // key一般不用加
            kafkaTemplate.send(topic, key, msg);
        } catch (Exception e) {
            e.printStackTrace();
            return "send fail";
        }
        return "send success";
    }

    /**
     * 监听消费
     * @param cr
     */
    @KafkaListener(topics = {KafkaConstant.KAFKA_TOPIC})
    public void neusoftEnergyData(ConsumerRecord<?, ?> cr) {
        Optional<?> messages = Optional.ofNullable(cr.value());
        messages.ifPresent(o -> fixedThreadPool.execute(() -> {
            String content = o.toString();
            log.debug("get data");
            try {
//                Map<String, Object> data = JSON.parseObject(content, Map.class);
                System.out.println(content);
            } catch (Exception e) {
                log.warn("dataReceiver message content illegal:" + content, e);
            }
        }));
    }

}

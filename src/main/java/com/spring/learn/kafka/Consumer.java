package com.spring.learn.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * Created on 2017/7/17.
 * Title: Simple
 * Description: Example
 * Copyright: Copyright(c) 2016
 * Company: 杭州公共交通云科技有限公司
 *
 * @author 维斯
 */
@Service
public class Consumer {
    @KafkaListener(topics = "STOP_SIGN_ROUTE_RELATION")
    public void consume(String message) throws IOException {
        System.out.println(String.format("#### -> Consumed message -> %s", message));
    }

    @KafkaListener(topics = "test12")
    public void listen(ConsumerRecords<String,String> records) {
        records.forEach(record -> {
            String value = record.value();
            String topic = record.topic();
            Optional<String> optionalValue = Optional.ofNullable(value);
            if (optionalValue.isPresent()) {
                System.out.println(value);
            }
        });

    }
}
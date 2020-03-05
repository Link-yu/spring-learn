package com.spring.learn;

import com.spring.learn.kafka.KafkaConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created on 2017/7/17.
 * Title: Simple
 * Description: Example
 * Copyright: Copyright(c) 2016
 * Company: 杭州公共交通云科技有限公司
 *
 * @author 维斯
 */
@Component
@Order(value = 2)
public class Handler implements CommandLineRunner {
    private KafkaConsumer kafkaConsumer;
    @Autowired
    private KafkaConsumerConfig kafkaConsumerConfig;

    public void onMessage() {
        kafkaConsumer.subscribe(Collections.singleton("synchronization_increment_data"));
        try{
            do {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
                if(records != null && !records.isEmpty()){
                    for (ConsumerRecord consumerRecord:records) {
                        System.out.println(consumerRecord);
                    }
                }
            } while (!Thread.currentThread().isInterrupted());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            kafkaConsumer.commitSync();
        }
    }

    public void init() {
        kafkaConsumer = new KafkaConsumer(kafkaConsumerConfig.consumerConfig());
    }
    @Override
    public void run(String... args) throws Exception {
//        init();
//        onMessage();
    }
}

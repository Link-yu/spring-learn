package com.spring.learn.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
    @KafkaListener(topics = "synchronization_increment_data")
    public void consume(String message) throws IOException {
        System.out.println(String.format("#### -> Consumed message -> %s", message));
    }
}
package com.spring.learn.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

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
public class Producer {

    private static final String TOPIC = "STOP_SIGN_ROUTE_RELATION";

    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        System.out.println(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC, "{\"action\":0,\"cubeId\":2,\"stopSignNo\":\"111111\",\"routeName\":\"1路\",\"routeId\":1000100001,\"stopName\":\"龙翔桥\",\"stopId\":1001000002,\"direction\":4,\"seqNo\":5,\"cityCode\":\"330100\",\"accountId\":11110,\"createTime\":\"2020-03-05T15:26:03.426\"}\n");
    }
}

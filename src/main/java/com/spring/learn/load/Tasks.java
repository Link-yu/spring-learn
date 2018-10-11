package com.spring.learn.load;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Tasks {
    private static final SimpleDateFormat date= new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void print() {
        System.out.println("现在时间:" + date.format(new Date()));
    }
}

package com.spring.learn.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustonListener implements ApplicationListener<TestEvent> {
    @Override
    public void onApplicationEvent(TestEvent testEvent) {
        System.out.println(testEvent.getName() + " " + testEvent.getDoing());
    }
}

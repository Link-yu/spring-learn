package com.spring.learn.controller;

import com.spring.learn.listener.CustomerEventPublisher;
import com.spring.learn.service.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthController {
    @Autowired(required = false)
    private CustomerEventPublisher customerEventPublisher;
    @Autowired(required = false)
    private ExecutorService executorService;
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public void sayHello() {
//        customerEventPublisher.publish();
        executorService.executor();
        System.out.println("success!");
    }

    @RequestMapping(value = "/health2", method = RequestMethod.GET)
    public void add() {
        executorService.executor2();
    }
}

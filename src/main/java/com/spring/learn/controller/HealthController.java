package com.spring.learn.controller;

import com.spring.learn.listener.CustomerEventPublisher;
import com.spring.learn.service.ExecutorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthController {
    private static Logger log = Logger.getLogger(HealthController.class);
    @Autowired(required = false)
    private CustomerEventPublisher customerEventPublisher;
    @Autowired(required = false)
    private ExecutorService executorService;

    public HealthController() {
        System.out.println("health!");
    }
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public void sayHello() {
//        customerEventPublisher.publish();
//        executorService.executor();
        log.info("success");
        System.out.println("success!");
    }

    @RequestMapping(value = "/health2", method = RequestMethod.GET)
    public void add() {
        executorService.executor2();
    }
}

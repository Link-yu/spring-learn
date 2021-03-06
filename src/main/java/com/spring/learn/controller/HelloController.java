package com.spring.learn.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.learn.Dao.LogMapper;
import com.spring.learn.Dao.UserMapper;
import com.spring.learn.impl.UserServiceImpl;
import com.spring.learn.kafka.Producer;
import com.spring.learn.listener.TestEvent;
import com.spring.learn.model.Blog;
import com.spring.learn.model.SysLog;
import com.spring.learn.model.User;
import com.spring.learn.service.UserService;
import com.spring.learn.util.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * @author yu
 * @date 2016/10/31
 */
//@Api
@RestController
@RequestMapping(value = "/index")
@AutoConfigureAfter(UserServiceImpl.class)
public class HelloController {
    protected static final Logger logger = Logger.getLogger(HelloController.class);
    @Autowired
    private Blog blog;
    @Autowired(required = false)
    private UserMapper userDao;
    @Autowired(required = false)
    private LogMapper logMapper;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private UserService userService;


//    public HelloController() {
//        System.out.println("hekk");
//    }

    private final Producer producer;

    @Autowired
    HelloController(Producer producer) {
        this.producer = producer;
    }

    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void sayHello(@RequestParam(required = true) String name) {
        System.out.print("say hello to " + name);
    }

//    @ApiOperation(value = "获取用户列咖妃嘎嘎表", notes = "获取用户列的阿发尕尕333表")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
//        logger.info("query db");
//        System.out.print("dsfaga");
//        userService.findAllUser();
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,5,10,TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
//        executor.execute(userService);
        userService.batchUser();
        return "success";
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createUser(@RequestParam(required = true) String name,
                             @RequestParam(required = true) String id) {
        logger.info("start to add {}");
        System.out.print(name + "sefge");
        User user = new User();
        user.setId(id);
        System.out.print("dfaga4323444444");
        user.setUsername(name);
        try {
            userService.addUser(user);
        } catch (Exception e) {
            logger.info("exception");
        }


        return "success";
    }

//    @ApiOperation(value = "获取用户信息", notes = "根据用户id获取用户信息")
//    @ApiImplicitParam(name = "id", value = "用户id",required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String findUser(@RequestParam(required = true) String id) throws Exception {
//        logger.info("query user by {}", id);
            User user = userService.findUser(id);
        String s = objectMapper.writeValueAsString(user);
        return s;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(@RequestParam(required = true) String id) {
        userService.delete(id);
//        logger.info("delete user id is {}.", id);
    }

}

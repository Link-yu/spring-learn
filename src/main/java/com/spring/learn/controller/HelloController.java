package com.spring.learn.controller;


import com.spring.learn.Dao.LogMapper;
import com.spring.learn.Dao.UserMapper;
import com.spring.learn.model.Blog;
import com.spring.learn.model.SysLog;
import com.spring.learn.model.User;
import com.spring.learn.util.Log;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 *
 * @author yu
 * @date 2016/10/31
 */
@RestController
@RequestMapping(value = "/index")
public class HelloController {
    protected static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private Blog blog;
    @Autowired(required = false)
    private UserMapper userDao;
    @Autowired(required = false)
    private LogMapper logMapper;
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void sayHello(@RequestParam(required = true) String name) {
        System.out.print("say hello to " + name);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        logger.info("query db");
        userDao.findAllUser();
        return "success";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createUser(@RequestParam(required = true) String name) {
        logger.info("start to add {}", name);
        User user = new User();
        user.setId("2");
        user.setUsername("yupaopao");
        userDao.createUser(user);
        return "success";
    }

}

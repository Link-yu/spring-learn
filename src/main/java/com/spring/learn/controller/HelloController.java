package com.spring.learn.controller;


import com.spring.learn.Dao.LogMapper;
import com.spring.learn.Dao.UserMapper;
import com.spring.learn.model.Blog;
import com.spring.learn.model.SysLog;
import com.spring.learn.model.User;
import com.spring.learn.util.Log;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author yu
 * @date 2016/10/31
 */
@RestController
@RequestMapping(value = "/index")
public class HelloController {

    @Autowired
    private Blog blog;
    @Autowired(required = false)
    private UserMapper userDao;
    @Autowired
    private LogMapper logMapper;
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void sayHello(@RequestParam(required = true) String name) {
        System.out.print("say hello to " + name);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Log
    public String index() {
        return "success";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createUser(@RequestParam(required = true) String name) {
        User user = new User();
        user.setId("1");
        user.setUsername("yupaopao");
        userDao.createUser(user);
        return "success";
    }
}

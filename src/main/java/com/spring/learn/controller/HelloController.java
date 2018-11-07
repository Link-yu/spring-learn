package com.spring.learn.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.learn.Dao.LogMapper;
import com.spring.learn.Dao.UserMapper;
import com.spring.learn.impl.UserServiceImpl;
import com.spring.learn.model.Blog;
import com.spring.learn.model.SysLog;
import com.spring.learn.model.User;
import com.spring.learn.util.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;

/**
 *
 * @author yu
 * @date 2016/10/31
 */
@Api
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
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void sayHello(@RequestParam(required = true) String name) {
        System.out.print("say hello to " + name);
    }

    @ApiOperation(value = "获取用户列咖妃嘎嘎表", notes = "获取用户列的阿发尕尕333表")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        logger.info("query db");
        userService.findAllUser();
        return "success";
    }
    @ApiIgnore
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createUser(@RequestParam(required = true) String name) {
        logger.info("start to add {}", name);
        User user = new User();
        user.setId("3");
        user.setUsername(name);
        userService.addUser(user);
        return "success";
    }

    @ApiOperation(value = "获取用户信息", notes = "根据用户id获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户id",required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String findUser(@RequestParam(required = true) String id) throws Exception {
        logger.info("query user by {}", id);
        User user = userService.findUser(id);
        String s = objectMapper.writeValueAsString(user);
        return s;
    }

}

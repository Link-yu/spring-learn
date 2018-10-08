package com.spring.learn.impl;

import com.spring.learn.Dao.UserMapper;
import com.spring.learn.model.User;
import com.spring.learn.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired(required = false)
    private UserMapper userMapper;
    @Override
    public void addUser(User user) {
        userMapper.createUser(user);
        logger.info("add user {}", user.getUsername());
    }

    @Override
    public List<User> findAllUser() {
        List<User> list = userMapper.findAllUser();
        return list;
    }

    @Override
    public User findUser(String id) {
        User user = userMapper.findUser(id);
        return user;
    }
}

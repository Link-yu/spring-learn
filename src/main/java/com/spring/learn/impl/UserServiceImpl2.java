package com.spring.learn.impl;

import com.spring.learn.model.User;
import com.spring.learn.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "service2")
public class UserServiceImpl2 implements UserService {
    @Override
    public void addUser(User user) throws Exception {
        System.out.println("use method 2!");
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }

    @Override
    public User findUser(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void batchUser() {

    }
}

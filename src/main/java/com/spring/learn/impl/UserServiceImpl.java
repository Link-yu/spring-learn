package com.spring.learn.impl;

import com.spring.learn.Dao.UserMapper;
import com.spring.learn.listener.TestEvent;
import com.spring.learn.model.User;
import com.spring.learn.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService, Runnable {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(User user) throws Exception{
        userMapper.createUser(user);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                applicationEventPublisher.publishEvent(new TestEvent(this,"tom","running"));
            }
        });
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

    @Override
    public void delete(String id) {
        userMapper.delete(id);
    }


    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                System.out.print(i++);
                Thread.sleep(1000);
            }
        } catch (Exception e) {

        }
    }
}

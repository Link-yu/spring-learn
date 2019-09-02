package com.spring.learn.impl;

import com.spring.learn.Dao.UserMapper;
import com.spring.learn.listener.TestEvent;
import com.spring.learn.model.User;
import com.spring.learn.service.UserService;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserServiceImpl implements UserService, Runnable {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired(required = false)
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired(required = false)
    private MongoTemplate mongoTemplate;

    public UserServiceImpl() {
        System.out.println("UserService!");
    }
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
//        userMapper.delete(id);
        Map<String, Object> records = new HashMap<>(4);
        records.put("name", "小灰灰Blog");
        records.put("github", "https://github.com/liuyueyi");
        records.put("time", LocalDateTime.now());

        mongoTemplate.save(records, "MongoTest");

    }

    @Override
    public void batchUser() {
        threadPoolTaskExecutor.submit(() -> {
            batch();
        });
    }

    @PostConstruct
    public void test() {
        Map<String, Object> records = new HashMap<>(4);
        records.put("name", "小灰灰Blog");
        records.put("github", "https://github.com/liuyueyi");
        records.put("time", LocalDateTime.now());
        List<User> list = userMapper.findAllUser();
//        mongoTemplate.save(records, "MongoTest");
        System.out.println("@PostConstruct");
    }
    private void batch() {
        for (int i = 0; i < 100;i++) {
            User user = new User();
            user.setId(String.valueOf(i));
            user.setUsername("杰尔玛:" + i);
            userMapper.createUser(user);
        }
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

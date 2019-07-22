//package com.spring.learn.impl;
//
//import com.spring.learn.model.User;
//import com.spring.learn.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service(value = "service2")
//public class UserServiceImpl2 implements UserService {
//    @Autowired
//    @Qualifier(value = "secondaryTemplate")
//    private JdbcTemplate jdbcTemplate;
//    //JDBC要编写大量的sql语句
//    @Override
//    public void addUser(User user) throws Exception {
////        System.out.println("use method 2!");
//        jdbcTemplate.update("insert into user(id, username) values(?, ?)", user.getId(), user.getUsername());
//    }
//
//    @Override
//    public List<User> findAllUser() {
//        return null;
//    }
//
//    @Override
//    public User findUser(String id) {
//        return null;
//    }
//
//    @Override
//    public void delete(String id) {
//
//    }
//
//    @Override
//    public void batchUser() {
//
//    }
//}

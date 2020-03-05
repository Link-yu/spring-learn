package com.spring.learn.Dao;

import com.spring.learn.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper()
public interface UserMapper {
    void createUser(User user);

    List<User> findAllUser();

    User findUser(String id);

    void delete(String id);
}

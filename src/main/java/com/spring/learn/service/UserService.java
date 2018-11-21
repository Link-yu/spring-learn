package com.spring.learn.service;

import com.spring.learn.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "user")
public interface UserService {

    @CachePut(key = "#p0.id")
    void addUser(User user);

    @Cacheable()
    List<User> findAllUser();

    @Cacheable(key = "#p0")
    User findUser(String id);

    void delete(String id);
}

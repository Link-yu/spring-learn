package com.spring.learn.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@PropertySource("classpath:test.properties")
@ConfigurationProperties(prefix = "blog")
@Data
@Component
public class Blog {
    private String name;
    private String author;
}

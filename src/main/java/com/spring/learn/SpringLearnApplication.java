package com.spring.learn;

import com.spring.learn.model.Blog;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({Blog.class})
public class SpringLearnApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringLearnApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}

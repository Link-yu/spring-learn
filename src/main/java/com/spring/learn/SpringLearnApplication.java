package com.spring.learn;

import com.spring.learn.model.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({Blog.class})
@EnableCaching
@EnableScheduling
@ServletComponentScan
@ImportResource("classpath:time.xml")
public class SpringLearnApplication {
    protected static final Logger logger = LoggerFactory.getLogger(SpringLearnApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringLearnApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}

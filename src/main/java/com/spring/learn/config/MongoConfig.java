package com.spring.learn.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Created on 2017/7/17.
 * Title: Simple
 * Description: Example
 * Copyright: Copyright(c) 2016
 * Company: 杭州公共交通云科技有限公司
 *
 * @author 维斯
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
    @Value("${spring.data.mongodb.database}")
    private String userName;

    @Value("${spring.data.mongodb.url}")
    private String mongoUrl;
    @Override
    public MongoClient mongoClient() {
        MongoClientURI connectionString = new MongoClientURI(mongoUrl);
        MongoClient mongoClient = new MongoClient(connectionString);
        return mongoClient;
    }

    @Override
    protected String getDatabaseName() {
        return userName;
    }

    @Override
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), userName);
    }
}

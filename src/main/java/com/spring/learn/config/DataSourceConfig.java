package com.spring.learn.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

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
public class DataSourceConfig {

//    @Bean(name = "primaryDataSourcePropeties")
//    @Qualifier("primaryDataSourcePropeties")
//    @ConfigurationProperties(prefix = "spring.datasource.primary")
//    public DataSourceProperties primaryDataSourcePro() {
//        return new DataSourceProperties();
//    }
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
//        return primaryDataSourcePro().initializeDataSourceBuilder().build();
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(value = "primaryTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier(value = "primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

//    @Bean(value = "secondaryTemplate")
//    public JdbcTemplate secondaryJdbcTemplate(@Qualifier(value = "secondaryDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
}

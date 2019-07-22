package com.spring.learn.middle;

import com.spring.learn.annotion.LoadClass;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
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
@LoadClass(value = "route")
@Order(value = 1)
public class IdService implements InitializingBean, CommandLineRunner, DisposableBean, ApplicationRunner {
    @Override
    public void destroy() throws Exception {
        System.out.println("bean has been destry!");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner!");
        Thread.sleep(20000);
        System.out.println("asdsga");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("start InitializingBean");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("start ApplicationRunner");
    }
}

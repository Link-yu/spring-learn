package com.spring.learn.middle;

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
@Component
@Order(value = 2)
public class Test implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("test222");
    }
}

package com.spring.learn.impl;

import com.spring.learn.service.ExecutorService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ExecutorServiceImpl implements ExecutorService {
    private Integer num = 11;
    @Override
    @Async("asyncServiceExecutor")
    public void executor() {
        try{
            System.out.println("小红取出5块");
            Thread.sleep(5000);
            num = num-5;
            System.out.println("现在账户还有: " + num);
        } catch (Exception e) {
        }
    }

    @Override
    @Async("asyncServiceExecutor")
    public void executor2() {
        try{
            Thread.sleep(2000);
            System.out.println("我取出5块");
            if (num > 5) {
                num = num-5;
                System.out.println("现在还有存款：" + num);
            } else  {
                System.out.println("余额不足！");
            }
        } catch (Exception e) {
        }
//        System.out.println("end executor2");
    }
}

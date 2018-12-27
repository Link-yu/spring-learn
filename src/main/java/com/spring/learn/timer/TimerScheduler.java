package com.spring.learn.timer;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
public class TimerScheduler {
    private static final SimpleDateFormat date= new SimpleDateFormat("HH:mm:ss");
    //fixedRata:每隔多长时间执行一次；
    @Scheduled(fixedRate = 1000*10)
    public void printTime() {
        System.out.println("每隔10秒" + "+ 现在时间:" + date.format(new Date()));
    }

    //其中每个元素可以是一个值(如6),一个连续区间(9-12),一个间隔时间(8-18/4)(/表示每隔4小时),一个列表(1,3,5),通配符。
    //* 表示所有值。 例如:在分的字段上设置 *,表示每一分钟都会触发。
    //? 表示不指定值。使用的场景为不需要关心当前设置这个字段的值。
    @Scheduled(cron = "0 */5 *  * * * ")
    public void printTimeByCron() {
        System.out.println("定时" + "+ 现在时间:" + date.format(new Date()));
    }
}

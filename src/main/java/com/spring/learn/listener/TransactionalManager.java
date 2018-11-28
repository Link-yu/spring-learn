package com.spring.learn.listener;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TransactionalManager {

    @TransactionalEventListener(phase= TransactionPhase.AFTER_COMMIT)
    public void handle(TestEvent excelEvent){
        System.out.println(excelEvent.getName() + " " + excelEvent.getDoing());
    }

    @TransactionalEventListener(phase= TransactionPhase.AFTER_COMMIT)
    public void handle2(AmapEvent excelEvent){
        System.out.println(excelEvent.getId());
    }
}

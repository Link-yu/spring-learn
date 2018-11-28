package com.spring.learn.listener;

import org.springframework.context.ApplicationEvent;

public class AmapEvent extends ApplicationEvent {
    private Integer id;
    public AmapEvent(Object source, Integer id) {
        super(source);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

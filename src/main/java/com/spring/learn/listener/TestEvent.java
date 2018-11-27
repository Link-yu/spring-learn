package com.spring.learn.listener;

import org.springframework.context.ApplicationEvent;

public class TestEvent extends ApplicationEvent {
    private String name;
    private String doing;
    public TestEvent(Object source,String name,String doing ) {
        super(source);
        this.name = name;
        this.doing = doing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoing() {
        return doing;
    }

    public void setDoing(String doing) {
        this.doing = doing;
    }
}

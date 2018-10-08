package com.spring.learn.load;

import com.spring.learn.impl.UserServiceImpl;
import com.spring.learn.model.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class LoadTaskStart implements InitializingBean, ApplicationContextAware{
    private ApplicationContext applicationContext;
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("loadTaskStart!");
        doLoad();
        System.out.println("doLoad done!");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private void doLoad() {
//        User user = applicationContext.getBean(User.class);
//        System.out.println(user.toString());
    }
}

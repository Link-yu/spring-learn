package com.spring.learn.load;

import com.spring.learn.annotion.LoadClass;
import com.spring.learn.impl.UserServiceImpl;
import com.spring.learn.middle.IdService;
import com.spring.learn.middle.SpringManager;
import com.spring.learn.model.User;
import com.spring.learn.reflect.ClassesUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class LoadTaskStart implements InitializingBean, ApplicationContextAware, EnvironmentAware {
    private ApplicationContext applicationContext;
    private Environment environment;
    @Autowired(required = false)
    private SpringManager springManager;
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
        try {
            if (environment != null) {
                String property = environment.getProperty("load.data");
                if (property != null && property.equals("route")) {
                    loadBeans();
                }
            }
        } catch(Exception e) {

        }

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    private void loadBeans() throws Exception{
        Set<Class<?>> classes = ClassesUtils.getClassesByAnnotation("com.spring.learn.middle", LoadClass.class);
        if(classes != null && !classes.isEmpty()){
            classes.forEach(itemClass -> {
                /*-----只有配置了需要加载的数据 load.data = route,bus,stop…… --对应的实体才会实例化------SINGLETON_LOADS----value*/
                String property = this.environment.getProperty("load.data");
                if(property != null){
                    String[] loads = property.split(",");
                    final List<String> loadItems  = Arrays.asList(loads);
                    loadItems.forEach(loadItem -> {
                        LoadClass annotation = itemClass.getAnnotation(LoadClass.class);
                        if(annotation != null && annotation.value().equals(loadItem)){
                            springManager.registryBean(itemClass.getSimpleName(),itemClass);
                            Object bean = applicationContext.getBean(itemClass.getSimpleName());
//                            addLoadHandle(itemClass.getSimpleName(),bean);
                        }
                    });
                }
            });
        }
    }
}

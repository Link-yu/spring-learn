package com.spring.learn.middle;

import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
public class SpringManager implements ApplicationContextAware, EnvironmentAware, BeanFactoryPostProcessor {
    private ApplicationContext applicationContext = null;
    private ConfigurableApplicationContext configurableContext=null;
    private BeanDefinitionRegistry beanDefinitionRegistry =null;
    private Environment environment;
    private static final Lock LOCK = new ReentrantLock();

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        try {
            LOCK.lock();
            if (applicationContext != null){
                this.applicationContext=applicationContext;
                this.configurableContext = (ConfigurableApplicationContext)applicationContext;
                this.beanDefinitionRegistry =  (DefaultListableBeanFactory)configurableContext.getBeanFactory();
            }
        } catch (Exception e){
//            LogUtils.info(logger,"spring context utils set application fail !", LogLevelEnum.P1,"");
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }

    }


    public Object getBeanByClass(Class itemClass){
        if (itemClass != null){
            return applicationContext.getBean(itemClass);
        }
        return null;
    }

    /**
     * 手动注册bean到容器
     * @param beanName
     * @param beanClass
     * @return
     */
    public boolean registryBean(String beanName,Class beanClass){
        if (beanDefinitionRegistry != null && beanName != null && beanClass != null){
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
            BeanDefinition definition = builder.getBeanDefinition();
            beanDefinitionRegistry.registerBeanDefinition(beanName, definition);
            return true;
        }
        return false;
    }



    /**
     * 手动卸载容器中的bean
     * @param beanName
     * @return
     */
    public boolean removeBean(String beanName){
        if (beanDefinitionRegistry != null && beanName != null){
            beanDefinitionRegistry.removeBeanDefinition(beanName);
            return true;
        }
        return false;
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }
}

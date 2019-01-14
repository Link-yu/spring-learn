package com.spring.learn.annotion;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface LoadClass {
    String value() default "";
}

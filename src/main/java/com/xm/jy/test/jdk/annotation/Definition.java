package com.xm.jy.test.jdk.annotation;

import java.lang.annotation.*;

/**
 * @author: albert.fang
 * @date: 2021/1/7 10:23
 * @description: 自定义注解
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Definition {
    String name();
    int age();
    String address() default "江西省上饶市广丰区大石乡新区路36号";
}

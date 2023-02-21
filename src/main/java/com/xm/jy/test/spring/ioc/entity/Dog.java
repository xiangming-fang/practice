package com.xm.jy.test.spring.ioc.entity;

import org.springframework.stereotype.Component;

/**
 * @auther 方翔鸣
 * @date 2020/3/8 22:21
 */
@Component
public class Dog implements Pet{
    @Override
    public void call() {
        System.out.println("dog running。。。");
    }
}

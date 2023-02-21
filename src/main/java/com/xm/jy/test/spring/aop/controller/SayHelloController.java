package com.xm.jy.test.spring.aop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther 方翔鸣
 * @date 2020/3/9 10:01
 */
@RestController
public class SayHelloController {

    @RequestMapping(value = "/getHello")
    public String getHello(){
        return "hello world";
    }
}

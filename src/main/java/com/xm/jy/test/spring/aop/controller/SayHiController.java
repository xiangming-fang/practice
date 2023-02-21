package com.xm.jy.test.spring.aop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther 方翔鸣
 * @date 2020/3/9 10:35
 */
@RestController
public class SayHiController {

    @RequestMapping(value = "/getHi")
    public String getHi(){
        return "hi world";
    }
}

package com.xm.jy.test.spring.ioc.aware;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author: albert.fang
 * @date: 2020/12/25 16:34
 * @description: 创建此类，用于观察Beans
 */

@Component
public class DetectBeans {

    /**
     * 使用这个bean,不是调用这个方法，而是直接获取这个bean的返回值
     * @return
     */
    @Bean(name = "intheclass")
    public String methodInTheClass(){
        return "我是在class里的bean,并不是一个class；并且这个方法的返回值不能是void，一定要有一个返回值";
    }

    @Bean
    public String methodInTheClass2(){
        return "@Bean方法所处的类，一定要加上可以依赖注入的注解，例如：@Component,@Configuration";
    }
}

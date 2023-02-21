package com.xm.jy.test.spring.ioc.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @auther 方翔鸣
 * @date 2020/3/8 22:07
 * @Component和@ComponentScan注解组合使用，扫描和装配Bean到ioc容器中
 * 需要提一下的是@ComponentScan注解在启动类的@SpringBootApplication注解里
 */
@Component("people") // 表示将People类加载到ioc容器里
@Data
public class People {
    @Value("1")
    private Integer id;
    @Value("于欣欣")
    private String name;

    @Autowired
    private Pet pet;

    public void say(){
        pet.call();
    }
}

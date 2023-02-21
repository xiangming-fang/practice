package com.xm.jy.test.spring.ioc.config;

import com.xm.jy.test.spring.ioc.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @auther 方翔鸣
 * @date 2020/3/8 21:54
 * @Configuration和@Bean组合使用是将扫描全项目中的Bean，并将扫描到的bean装配到IOC容器中
 */
@Configuration
public class PersonConfig {

    @Bean
    public Person getPerson(){
        Person person = new Person();
        person.setId(1);
        person.setName("方翔鸣");
        return person;
    }

    @Bean
    @Primary
    public Person getOne(){
        Person person = new Person();
        person.setId(2);
        person.setName("林就远");
        return person;
    }
}

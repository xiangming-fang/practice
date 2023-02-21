package com.xm.jy.test.jdk.io;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: albert.fang
 * @date: 2020/12/28 10:45
 * @description: 单元测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FxmTest implements ApplicationContextAware {


//    @Resource(type = String.class)
//    private String intheclass;
//
//    @Resource(type = String.class)
//    private String methodInTheClass2;

    @Autowired @Qualifier("intheclass")
    private String intheclass1;

    @Autowired @Qualifier("methodInTheClass2")
    private String methodInTheClass21;

    private ApplicationContext ctx;


    /**
     * 使用@Autowried来注入使用@Bean注入到IOC容器里的beans
     */
    @Test
    public void testBeanAnnotation(){
        System.out.println(intheclass1);
        System.out.println(methodInTheClass21);
    }

    @Test
    public void printIOCBeans(){
        for (String beanDefinitionName : ctx.getBeanDefinitionNames()) {
            System.out.println("Bean >>> " + beanDefinitionName);
        }
        System.out.println("*********************");
        System.out.println(ctx.getBeanDefinitionCount());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}

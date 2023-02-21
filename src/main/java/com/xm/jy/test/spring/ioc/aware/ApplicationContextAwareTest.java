package com.xm.jy.test.spring.ioc.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author: albert.fang
 * @date: 2020/12/25 17:36
 * @description: ApplicationContextAwareTest
 * ApplicationContext只有在项目运行的时候才可以使用；通过主函数形式测，测不通。
 */
@Service
public class ApplicationContextAwareTest implements ApplicationContextAware {

    public ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    /**
     * 不能通过main函数来测，这样会报空指针
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContextAwareTest applicationContextAwareTest = new ApplicationContextAwareTest();
        for (String beanDefinitionName : applicationContextAwareTest.ctx.getBeanDefinitionNames()) {
            System.out.println("Bean <<< " + beanDefinitionName);
        }
        System.out.println("共有：" + applicationContextAwareTest.ctx.getBeanDefinitionCount()+"个");
    }
}

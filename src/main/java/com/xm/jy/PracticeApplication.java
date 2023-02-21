package com.xm.jy;

import com.xm.jy.test.spring.ioc.aware.ApplicationContextAwareTest;
import com.xm.jy.test.spring.ioc.aware.DetectBeans;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author albert.fang
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.xm.jy.job_cx.dao")
@EnableAsync
@ComponentScan(basePackages = {"com.xm.jy"})
public class PracticeApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(PracticeApplication.class, args);
        System.out.println("获取ApplicationContext这种IOC容器里的Beans");
        for (String beanDefinitionName : ctx.getBeanDefinitionNames()) {
            System.out.println("Bean >>> " + beanDefinitionName);
        }
        System.out.println("共有：" + ctx.getBeanDefinitionCount()+"个");
        System.out.println(ctx.getBean("methodInTheClass2",String.class));
        System.out.println(ctx.getBean("detectBeans", DetectBeans.class));
        // 比较通过ApplicationContextWare获得的applicationContext是否和SpringApplication.run获取到的一致
        // 结论：一致的，同一个applicationContext对象
        ApplicationContextAwareTest applicationContextAwareTest = ctx.getBean("applicationContextAwareTest",ApplicationContextAwareTest.class);
        System.out.println(applicationContextAwareTest.ctx.equals(ctx));

        // 看看上下文（IOC容器）对象里有哪些东西
        System.out.println("上下文的Id（应用的端口Id）：" + ctx.getId());
        System.out.println("上下文的applicationName：" + ctx.getApplicationName());
        System.out.println("上下文的displayName：" + ctx.getDisplayName());
        System.out.println("上下文的创建开始时间：" + new Date(ctx.getStartupDate()));
        System.out.println("上下文的parents：" + ctx.getParent());
        System.out.println("上下文的AutowireCapableBeanFactory：" + ctx.getAutowireCapableBeanFactory().toString());

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PracticeApplication.class);
    }
}

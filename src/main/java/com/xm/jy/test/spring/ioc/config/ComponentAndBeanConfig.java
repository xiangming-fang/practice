package com.xm.jy.test.spring.ioc.config;

import com.xm.jy.test.spring.ioc.entity.ComponentAndBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @auther 方翔鸣
 * @date 2020/3/10 20:29
 */
@Component
public class ComponentAndBeanConfig {

    @Bean
    public ComponentAndBean getComponentAndBean(){
        ComponentAndBean componentAndBean = new ComponentAndBean();
        componentAndBean.setBean("this is bean properties");
        componentAndBean.setComponent("this is component properties");
        return componentAndBean;
    }
}

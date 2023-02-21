package com.xm.jy.test.spring.ioc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author: albert.fang
 * @date: 2020/5/28 17:36
 * @description: 相信我，这次绝非是口头说说而已 mark 2020/5/28 17:36
 */
@Component
public class DayDayUpConfig {

    @Bean(name = "believeMe")
    public String believeMe(){
        return "    相信我，这次绝非是口头说说而已,mark 2020/5/28 17:36......"+"\033[0m";
    }
}

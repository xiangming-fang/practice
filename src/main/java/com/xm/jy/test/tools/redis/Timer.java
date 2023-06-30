package com.xm.jy.test.tools.redis;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.redis
 * @ClassName: Timer
 * @Author: albert.fang
 * @Description: 定时器
 * @Date: 2022/7/14 17:19
 */
//@Service
public class Timer {

    public static final String DELAY_QUEUE = "delayQueue";

    @Scheduled(cron = "0 * * * * ?")
    public void consumer(){
        long l = System.currentTimeMillis() / 1000 * 1000;
        System.out.println(l);
        List<String> msgs = RedisUtil.rangByScore(DELAY_QUEUE, l, l + 59999);
        // todo 消费
        System.out.println(msgs.toString());
        // 消费之后删除
        RedisUtil.delZsetValue(DELAY_QUEUE, l, l + 59999);
    }
}

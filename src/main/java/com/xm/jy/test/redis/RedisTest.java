package com.xm.jy.test.redis;

import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Set;

/**
 * @author: albert.fang
 * @date: 2020/8/3 17:10
 * @description: 连接、操作redis
 */
public class RedisTest {

    public static final String DELAY_QUEUE = "delayQueue";

    public static final String TIME = "2022-07-14 17:40";

    public static void main(String[] args) throws InterruptedException, ParseException {
        product();
//        consumer();
    }

    private static void product() throws ParseException {
        for (int i = 1; i <= 10; i++) {
            RedisUtil.zadd(DELAY_QUEUE,getTimeStamp(TIME) + i * 60000,"延迟"+i+"分钟发送");
        }
    }

    private static void consumer() throws InterruptedException {
        long first = System.currentTimeMillis();
        while (true){
            // 10s为一个range
            Set<String> msgs = RedisUtil.rangByScore(DELAY_QUEUE, first, first + 10000);
            if (CollectionUtils.isEmpty(msgs)){
                Thread.sleep(10000);
                first = first + 10000;
            }
            // todo 消费
            System.out.println(msgs.toString());
            // 消费之后删除
            RedisUtil.delZsetValue(DELAY_QUEUE,first,first + 10000);
            first = first + 10000;
        }
    }

    public static long getTimeStamp(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse(time);
        return date.getTime();
    }

    // 随机生成一个长度为6的字符串
    public static String getRandomString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            Random random = new Random();
            int j = random.nextInt(25) + 97;
            char zf = (char) j;
            sb.append(zf);
        }
        return sb.toString();
    }
}

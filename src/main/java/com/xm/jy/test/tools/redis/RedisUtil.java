package com.xm.jy.test.tools.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @auther 方翔鸣
 * @date 2020/3/10 19:36
 * 对StringRedisTemplate中的opsForValue和get和set方法
 */
public class RedisUtil {

    private static final Jedis JEDIS = RedisConnectPool.getConnection();

    /**
     * 不存在再插入
     */
    public static final String NX = "NX";

    /**
     * 存在再插入（覆盖）
     */
    public static final String XX = "XX";

    /**
     * 过期时间，单位秒
     */
    public static final String EX = "EX";

    /**
     * 过期时间，单位毫秒
     */
    public static final String PX = "PX";

    /************************** vlaue类型 为 String 部分 ********************************/

    public static String get(String key){
        return JEDIS.get(key);
    }

    public static void set(String key,String value){
        JEDIS.set(key,value);
    }

    public static void set(String key,String value,int expireSeconds){
        set(key,value,NX,EX,expireSeconds);
    }

    public static void set(String key,String value,long expireMillsSeconds){
        set(key,value,NX,PX,expireMillsSeconds);
    }

    public static void set(String key,String value,String nxxx,String expx,long time){
        JEDIS.set(key,value,nxxx,expx,time);
    }

    /************************** value类型 为 zset 部分 ********************************/

    public static Long zadd(String key,long score,String member){
        return JEDIS.zadd(key,score,member);
    }

    /**
     * 在区间【最小，最大】里偏移 【offset】个值之后，取【count】个元素值
     * @param key zset 的key
     * @param min 最小分数
     * @param max 最大分数
     * @param offset 偏移量
     * @param count 取几个
     * @return java.util.Set<java.lang.String>
     * @author Albert.fang
     * @date 2022/7/14 16:18
     */
    public static Set<String> rangByScore(String key, double min, double max, int offset, int count){
        return JEDIS.zrangeByScore(key, min, max, offset, count);
    }

    /**
     * 按范围查找zset里的值
     * @param key
     * @param min
     * @param max
     * @return java.util.Set<java.lang.String>
     * @author Albert.fang
     * @date 2022/7/14 16:53
     */
    public static Set<String> rangByScore(String key, double min, double max){
        return JEDIS.zrangeByScore(key, min, max);
    }

    /**
     * 按范围删除zset里的值
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Long delZsetValue(String key,long start,long end){
        return JEDIS.zremrangeByScore(key, start, end);
    }






}

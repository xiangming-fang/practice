package com.xm.jy.test.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: albert.fang
 * @date: 2020/8/3 17:15
 * @description: redis连接池
 */
public class RedisConnectPool {
    // 建立一个静态连接池对象
    public static JedisPool pool = null;

    static {
        // jedisPool的配置信息
        JedisPoolConfig config = new JedisPoolConfig();
        // TODO 一些配置信息暂时忽略，没怎么搞明白

        // 把配置信息给jedisPool，并填写客户端redis的相关信息
        pool = new JedisPool(config,"dev006-internal.softium.cn",6379,60000,null,18);
    }

    // 从连接池中得到一个redis连接
    public static Jedis getConnection(){
        return pool.getResource();
    }

}

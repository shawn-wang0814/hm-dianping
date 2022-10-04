package com.hmdp.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Project Name:hm-dianping
 * File Name:null.java
 * Package Name:com.hmdp.utils
 * Date:2022/10/4 14:45
 * Copyright (c) 2022, szxxwang@outlook.com All Rights Reserved.
 */
public class JedisConnectionFactory {

    private static final JedisPool jedisPool;

    static {
        //配置连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(8);
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMinIdle(0);
        jedisPoolConfig.setMaxWaitMillis(1000);
        //创建连接池对象
        jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379, 1000);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

}

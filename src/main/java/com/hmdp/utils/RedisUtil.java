package com.hmdp.utils;

import java.util.List;
import redis.clients.jedis.Jedis;
/**
 * Project Name:hm-dianping
 * File Name:null.java
 * Package Name:com.hmdp.utils
 * Date:2022/9/28 2:44
 * Copyright (c) 2022, szxxwang@outlook.com All Rights Reserved.
 */
public class RedisUtil {

    /**
     * 设置对象
     * @param key
     * @param obj
     */
    public static void setObject(String key ,Object obj){
        try {
            obj = obj == null ? new Object():obj;
            JedisConnectionFactory.getJedis().set(key.getBytes(), SerializeUtil.serialize(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象
     * @param key
     * @return Object
     */
    public static Object getObject(String key){
        if(JedisConnectionFactory.getJedis() == null || !JedisConnectionFactory.getJedis().exists(key)){
            return null;
        }
        byte[] data = JedisConnectionFactory.getJedis().get(key.getBytes());
        return (Object)SerializeUtil.unserialize(data);
    }

    /**
     * 设置List集合
     * @param key
     * @param list
     */
    public static void setList(String key ,List<?> list){
        try {

            if(CommonUtil.isNotNull(list)){
                JedisConnectionFactory.getJedis().set(key.getBytes(), SerializeUtil.serializeList(list));
            }else{//如果list为空,则设置一个空
                JedisConnectionFactory.getJedis().set(key.getBytes(), "".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取List集合
     * @param key
     * @return
     */
    public static List<?> getList(String key){
        if(JedisConnectionFactory.getJedis() == null || !JedisConnectionFactory.getJedis().exists(key)){
            return null;
        }
        byte[] data = JedisConnectionFactory.getJedis().get(key.getBytes());
        return SerializeUtil.unserializeList(data);
    }
}

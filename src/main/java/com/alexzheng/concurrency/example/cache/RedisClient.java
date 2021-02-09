package com.alexzheng.concurrency.example.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @Author Alex Zheng
 * @Date 2021/2/9 8:36
 * @Annotation
 */
@Component
public class RedisClient {

    @Resource(name = "redisPool")
    private JedisPool jedisPool;

    public void set(String key,String value) throws Exception{
        Jedis jedis = null;
        try{
           jedis = jedisPool.getResource();
           jedis.auth("123456");
           jedis.set(key,value);
        }finally {
            //释放资源
            jedis.close();
        }

    }

    public String get(String key) throws Exception{
        Jedis jedis = null;
        try{
           jedis = jedisPool.getResource();
            jedis.auth("123456");
           return jedis.get(key);
        }finally {
            //释放资源
            jedis.close();
        }

    }

}

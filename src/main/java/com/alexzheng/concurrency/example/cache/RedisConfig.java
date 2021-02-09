package com.alexzheng.concurrency.example.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @Author Alex Zheng
 * @Date 2021/2/9 8:30
 * @Annotation
 */
@Configuration
public class RedisConfig {

    @Value("${jedis.host}")
    private String host;

    @Value("${jedis.port}")
    private int port;

    @Bean(name = "redisPool")
    public JedisPool jedisPool(){
        return new JedisPool(host,port);
    }

}

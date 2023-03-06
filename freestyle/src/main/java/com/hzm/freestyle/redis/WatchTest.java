package com.hzm.freestyle.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.data.redis.connection.lettuce.LettuceConnection;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年01月07日
 */
public class WatchTest {

    public static void main(String[] args) {
        RedisURI redisURI = RedisURI.builder()
                .withHost("172.21.23.56")
                .withPort(6379)
                .build();
        RedisClient redisClient = RedisClient.create(redisURI);
        LettuceConnection lettuceConnection = new LettuceConnection(100L, redisClient);
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.watch("");
    }
}

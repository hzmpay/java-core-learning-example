package com.hzm.freestyle.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月20日
 */
@RestController
@RequestMapping
public class RedisProvider {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/sendRedis")
    public void sendRedis(String message) {

    }


}

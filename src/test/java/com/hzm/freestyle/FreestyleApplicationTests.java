package com.hzm.freestyle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class FreestyleApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {

        int i = 1;

    }

}

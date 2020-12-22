package com.hzm.freestyle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@SpringBootTest
class FreestyleApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        String key = "LoginUserName";

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put((i + 1) + "", (i + 1 + 100) + "");
        }
        redisTemplate.opsForHash().putAll(key, map);

        List<String> list = new ArrayList<>(500);
        IntStream.rangeClosed(1, 50).forEach(e -> list.add(e + ""));
        list.add("200");
        list.add("100");

        List list1 = redisTemplate.opsForHash().multiGet(key, list);

        System.out.println(list1);

        redisTemplate.delete(key);

    }

}

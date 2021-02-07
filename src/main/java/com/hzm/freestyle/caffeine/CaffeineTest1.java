package com.hzm.freestyle.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.time.Duration;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年02月04日
 */
@Slf4j
public class CaffeineTest1 {

    public static void main(String[] args) throws InterruptedException {
        Cache<Integer, Object> cache = Caffeine.newBuilder()
                // 数量限制
                .maximumSize(2)
//                .maximumWeight(2)
                // 过期时间
                .expireAfterWrite(Duration.ofMillis(1000))
                // 弱引用key
                .weakKeys()
                // 弱引用value
                .weakValues()
                // 删除监听
                .removalListener(new RemovalListener<Integer, Object>() {
                    @Override
                    public void onRemoval(@Nullable Integer key, @Nullable Object value, @NonNull RemovalCause removalCause) {
                        log.info("key = {}, value = {}, 删除原因 ：{} ", key, value, removalCause.toString());
                    }
                })
                .build();
        for (int i = 0; i < 15; i++) {
            cache.put(i, i);
//            Thread.sleep(500);
        }

        System.out.println(cache.asMap());
    }
}

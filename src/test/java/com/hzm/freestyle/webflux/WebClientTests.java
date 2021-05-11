package com.hzm.freestyle.webflux;

import com.hzm.freestyle.spring.webflux.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月11日
 */
@Slf4j
public class WebClientTests {

    @Test
    public void monoTest() {
        Mono<User> mono = WebClient.create("http://localhost:8888")
                .get()
                .uri("/user/info")
                .retrieve()
                .bodyToMono(User.class);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        mono.subscribe(e -> {
            log.info(e.toString());
            stopWatch.stop();
            log.info("本次请求总耗时：{} 秒", stopWatch.getTotalTimeMillis());
        });
        try {
            // 异步响应，等待返回，或者使用CountDownLatch
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void FluxTest() {
        Flux<User> flux = WebClient.create("http://localhost:8888")
                .get()
                .uri("/user/list2")
                .retrieve()
                .bodyToFlux(User.class);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        flux.subscribe(e -> {
            log.info(e.toString());
            stopWatch.stop();
            log.info("本次请求总耗时：{} 秒", stopWatch.getTotalTimeMillis());
        });
        try {
            // 异步响应，等待返回，或者使用CountDownLatch
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

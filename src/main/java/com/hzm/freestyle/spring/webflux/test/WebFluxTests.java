package com.hzm.freestyle.spring.webflux.test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月14日
 */
public class WebFluxTests {

    public static void main(String[] args) {
        List<String> list = IntStream.rangeClosed(1, 5).mapToObj(e -> e + "").collect(Collectors.toList());
        Mono.just(list).subscribe(e -> System.out.println(e));
        // 正常数据处理，错误信号处理，完成信号处理
        Flux.fromIterable(list).subscribe(e -> {
            if (e.equals(3 + "")) {
                int i = 1/0;
            }
            System.out.println(e);
            System.out.println(Thread.currentThread().getName());
        }, e -> System.out.println("错误为：" + e), () -> System.out.println("已完成"));

    }
}

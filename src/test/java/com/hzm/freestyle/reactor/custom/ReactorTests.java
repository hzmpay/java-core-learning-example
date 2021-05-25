package com.hzm.freestyle.reactor.custom;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月25日
 */
public class ReactorTests {

    @Test
    public void fluxArrayTest() {
        Flux.just(1, 2, 3, 4, 5).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("onSubscribe");
                subscription.request(3);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext : " + integer);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}

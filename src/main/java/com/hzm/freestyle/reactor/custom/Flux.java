package com.hzm.freestyle.reactor.custom;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月24日
 */
public abstract class Flux<T> implements Publisher<T> {

    public abstract void subscribe(Subscriber<? super T> var1);

    public static <T> Flux<T> just(T... data) {
        return new FluxArray<>(data);
    }
}

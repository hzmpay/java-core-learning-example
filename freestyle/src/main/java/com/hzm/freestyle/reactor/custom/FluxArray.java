package com.hzm.freestyle.reactor.custom;

import org.reactivestreams.Subscriber;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月24日
 */
public class FluxArray<T> extends Flux<T> {

    private T[] array;

    public FluxArray(T[] array) {
        this.array = array;
    }

    public void subscribe(Subscriber<? super T> var1) {
        var1.onSubscribe(new ArraySubscription(var1, array));
    }

}

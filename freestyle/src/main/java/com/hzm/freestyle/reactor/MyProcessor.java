package com.hzm.freestyle.reactor;

import org.reactivestreams.Processor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * 集合了MyPublisher和MySubscriber的功能
 *
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月24日
 */
public class MyProcessor<T, R> implements Processor<T, R> {

    @Override
    public void subscribe(Subscriber<? super R> subscriber) {

    }

    @Override
    public void onSubscribe(Subscription subscription) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}

package com.hzm.freestyle.reactor.custom;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月25日
 */
public class ArraySubscription<T> implements Subscription {

    final Subscriber<? super T> var1;
    final T[] array;
    int index;
    boolean canceled;

    public ArraySubscription(Subscriber<? super T> var1, T[] array) {
        this.var1 = var1;
        this.array = array;
    }

    @Override
    public void request(long l) {
        if (canceled) {
            return;
        }
        int length = array.length;
        for (int i = 0; i < l && index < length; i++) {
            var1.onNext(array[index++]);
        }
        if (index == length) {
            var1.onComplete();
        }
    }

    @Override
    public void cancel() {
        this.canceled = true;
    }
}

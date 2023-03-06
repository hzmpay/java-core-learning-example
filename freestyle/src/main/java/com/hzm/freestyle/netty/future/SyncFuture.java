package com.hzm.freestyle.netty.future;

import java.util.concurrent.*;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年06月10日
 */
public class SyncFuture implements Future {

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}

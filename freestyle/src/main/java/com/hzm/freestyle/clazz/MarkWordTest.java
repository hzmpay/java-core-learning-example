package com.hzm.freestyle.clazz;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年12月25日
 */
@Slf4j
public class MarkWordTest {

    public static final Object LOCK = new Object();

    /**
     * -XX:BiasedLockingStartupDelay=0
     *
     * @param args
     * @return void
     * @author Hezeming
     */
    public static void main(String[] args) throws InterruptedException {
//        System.out.println(ClassLayout.parseClass(Lock.class).toPrintable());

        log.debug(VM.current().details());

//        log.debug("hashcode:{}",Integer.toHexString(LOCK.hashCode()));
        log.debug(ClassLayout.parseInstance(LOCK).toPrintable());
        log.debug("线程ID ：{} ", Thread.currentThread().getId());

//        Thread t1 = new Thread(()->{
//            test();
//        });
//        Thread t2 = new Thread(()->{
//            test();
//        });
//        t1.start();
////        t1.join(); //打开注释为轻量级锁
//        t2.start();

    }

    static void test() {
        synchronized (LOCK) {
            try {
                Thread.sleep(1000);
                log.info(Thread.currentThread().getName());
                log.debug(ClassLayout.parseInstance(LOCK).toPrintable());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

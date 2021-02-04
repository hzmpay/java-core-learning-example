package com.hzm.freestyle.hook;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年02月02日
 */
public class JvmHookTest {

    static {
        // 注册Hook函数
        System.out.println("Hook函数准备注册 ==========》");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Hook函数执行了 =========》");
        }));
        System.out.println("Hook函数注册完毕 ==========》");
    }

    public static void main(String[] args) throws InterruptedException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExistJvmDisposableBean.class);
        context.registerShutdownHook();
        System.out.println("Spring程序启动了 ==========》");
        Thread.sleep(2000);

        System.out.println("Spring程序关闭了 ==========》");

    }
}

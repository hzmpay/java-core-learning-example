package com.hzm.freestyle.classLoader.hotdeployment;

import java.lang.reflect.Method;

/**
 * 热部署实现
 *
 * @author hezeming
 * @version 1.0
 * @date 2022年08月01日
 */
public class HotDeploymentTest {

    public static void main(String[] args) throws Exception {
        while (true) {
            run();
            Thread.sleep(2000);
        }
    }

    private static final String baseDir = "/Users/hezeming/IdeaProjects/java-core-learning-example/target/classes";
    private static final CustomClassLoader customClassLoader = new CustomClassLoader(baseDir, new String[]{"com.hzm.freestyle.classLoader.hotdeployment.Foo"});

    private static void run() throws Exception {
        Class clazz = customClassLoader.loadClass("com.hzm.freestyle.classLoader.hotdeployment.Foo");
        Method method = clazz.getMethod("say", new Class[]{});
        method.invoke(clazz.newInstance(), new Object[]{});
    }
}

package com.hzm.freestyle.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月23日
 */
public class JdkDongTaiProxy {


    public static void main(String[] args) {

        final DemoProxyProxyServiceImpl2 demoServiceImpl = new DemoProxyProxyServiceImpl2();

//        final ClassLoader classLoader = demoServiceImpl.getClass().getClassLoader();
//        final Class<?>[] interfaces = demoServiceImpl.getClass().getInterfaces();
        final ClassLoader classLoader = JdkDongTaiProxy.class.getClassLoader();
        final Class<?>[] interfaces = {DemoProxyService.class, DemoProxyService2.class};

        // 实际生成的代理类继承Proxy并实现了目标代理类所实现的所有接口
        // 例如：com.sun.proxy.$Proxy0 extend Proxy implements DemoProxyService, DemoProxyService2
        final Object o = Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理执行before....................");

                System.out.println("method = " + method);
                System.out.println("proxy = " + proxy.getClass());

                final Method proxyMethod = demoServiceImpl.getClass().getMethod(method.getName(), (Class<?>[]) args);
                final Object invoke = proxyMethod.invoke(demoServiceImpl, args);

//                final Object invoke = method.invoke(demoServiceImpl, args);
                System.out.println("代理执行after....................");
                return invoke;
            }
        });

        System.out.println(o.getClass());

        final Class<?>[] interfaces2 = o.getClass().getInterfaces();
        System.out.println(Arrays.toString(interfaces2));

        System.out.println(o instanceof Proxy);
        System.out.println(o instanceof DemoProxyService);
        System.out.println(o instanceof DemoProxyService2);

        ((DemoProxyService) o).say();
        ((DemoProxyService2) o).say2();

    }

}

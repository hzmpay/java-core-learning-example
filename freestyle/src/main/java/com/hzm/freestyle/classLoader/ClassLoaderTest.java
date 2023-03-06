package com.hzm.freestyle.classLoader;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月10日
 */

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器与instanceof关键字演示
 *
 * @author zzm
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object myObj = myLoader.loadClass("com.hzm.freestyle.classLoader.ClassLoaderTest").newInstance();
        System.out.println(myObj.getClass());
        System.out.println(myObj instanceof ClassLoaderTest);

        final Object defaultObj = ClassLoaderTest.class.getClassLoader().loadClass("com.hzm.freestyle.classLoader.ClassLoaderTest").newInstance();
        System.out.println(defaultObj.getClass());
        System.out.println(defaultObj instanceof ClassLoaderTest);
    }
}

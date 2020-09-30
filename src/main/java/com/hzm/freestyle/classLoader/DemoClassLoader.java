package com.hzm.freestyle.classLoader;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月24日
 */
public class DemoClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}

package com.hzm.freestyle.clazz;


import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年11月25日
 */
public class MethodTest {

    private String name;

    private Integer age;

    public MethodTest() {
    }

    public MethodTest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void say(String name1, Integer age1) {
    }

    public static void main(String[] args) throws Exception {

        Parameter[] parameters1 = MethodTest.class.getMethod("say", String.class, Integer.class).getParameters();
        for (Parameter parameter : parameters1) {
            System.out.println(parameter.getName());
        }

        Constructor<?>[] constructors = MethodTest.class.getConstructors();

        for (Constructor<?> constructor : constructors) {
            Parameter[] parameters = constructor.getParameters();
            System.out.println("============");
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getName());
            }
        }

    }
}

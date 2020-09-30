package com.hzm.freestyle.outOfMemoryError.methodArea;

import java.util.HashSet;
import java.util.Set;

/**
 * 运行时常量池
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年08月04日
 */
public class RuntimeConstantPoolOOM {

    /**
     * * VM Args：-XX:PermSize=6M -XX:MaxPermSize=6M（JDK6）
     * * VM Args：-Xms6m -Xmx6m（JDK8）
     * JDK7之后，intern常量池具有‘首次遇见’原则，首次遇见的字符串对象会在常量池里生成一个指向的引用
     *
     * @param args
     * @return void
     * @author Hezeming
     */
    public static void main(String[] args) {
//        jdk8();

//        String s = new StringBuilder().append("计算").append("机软件").toString();
//        s.intern();
//        s = null;
//        System.gc();

        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

//        str1 = null;
//        System.gc();

        String str2 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str2.intern() == str2);
    }


    public static void jdk8() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
        String a = "javaeeee";
        String str3 = new StringBuilder("ja").append("vaeeee").toString();
        System.out.println(str3.intern() == str3);
    }

    public static void demo() {
        String c = new StringBuilder("aba").append("abaa").toString();
        System.out.println(c.intern() == c);
//        c.intern();
        String b = new StringBuilder("a").append("baabaa").toString();
        System.out.println(b.intern() == b);
    }

    public static void jdk6() {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<String>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}

package com.hzm.freestyle.jvm;

/**
 * 常量池
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月23日
 */
public class ChangLiangChiTest {

    public static void main(String[] args) {
        final String a = new String("ab");

        String b = "ab";
        final String intern = a.intern();

        System.out.println(b == intern);

        System.out.println(a == intern);

    }
}

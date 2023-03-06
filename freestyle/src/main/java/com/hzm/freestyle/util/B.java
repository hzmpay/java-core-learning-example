package com.hzm.freestyle.util;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年11月11日
 */
public class B extends A {

    private String name;

    public B() {
        System.out.println("B");
    }

    public B(String name) {
        System.out.println("B has Args ==> " + name);
    }

    public static void main(String[] args) throws Exception {
        B b = BeanUtils.instantiateClass(B.class);
        System.out.println(b);

        Constructor<B> constructor = B.class.getDeclaredConstructor(String.class);
        B b1 = BeanUtils.instantiateClass(constructor, "哈哈");
        System.out.println(b1);

    }

    public static boolean isPalindrome(int x) {
        // 小于0 和 个位数是0的直接返回false（因为最高位不可能为0）
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            // 负数直接返回false
            return false;
        }

        int reverseNum = 0;
        // 直到原数字 小于（奇数位）或等于（偶数位）翻转的数时推出
        while (x > reverseNum) {
            reverseNum = reverseNum * 10 + x % 10;
            x = x / 10;
        }

        return x == reverseNum || x == reverseNum / 10;
    }

}

package com.hzm.freestyle.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月10日
 */
public class StatisValueTest {

    public static final List<String> list = new ArrayList<>(16);

    public static void main(String[] args) {
        while (true) {
            list.add("1");
        }

    }
}

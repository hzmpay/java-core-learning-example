package com.hzm.freestyle.jvm;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年07月01日
 */
public class JvmTest {

    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        for (int i = 0; true; i++) {
//            list.add(String.valueOf(i).intern());
//        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }

        final LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        final LocalDateTime now1 = LocalDateTime.now();

        System.out.println(Duration.between(now, now1).getNano());


        final LocalDateTime now2 = LocalDateTime.now();
        final Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        final LocalDateTime now3 = LocalDateTime.now();

        System.out.println(Duration.between(now2, now3).getNano());
    }
}

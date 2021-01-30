package com.hzm.freestyle.boss;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年01月14日
 */
public class User {

    private Integer age;

    private Integer getAge() {
        return age;
    }

    public static void remove(List<User> list) {
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getAge() > 20) {
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.age = 10;
        list.add(user1);
        for (int i = 0; i < 100000; i++) {
            User user2 = new User();
            user2.age = 30;
            list.add(user2);
        }
        long start = System.currentTimeMillis();
        User.remove(list);
        System.out.println(System.currentTimeMillis() - start);

        for (int i = 0; i < 100000; i++) {
            User user2 = new User();
            user2.age = 30;
            list.add(user2);
        }

        start = System.currentTimeMillis();
        list.removeIf(user -> user.getAge() > 20);
        System.out.println(System.currentTimeMillis() - start);

    }
}

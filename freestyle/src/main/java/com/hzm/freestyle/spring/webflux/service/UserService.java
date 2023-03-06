package com.hzm.freestyle.spring.webflux.service;

import com.google.common.collect.Lists;
import com.hzm.freestyle.spring.webflux.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月11日
 */
@Service
public class UserService {

    public User getUserInfo() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setName("测试");
        user.setAge(18);
        return user;
    }

    public List<User> list() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<User> list = Lists.newArrayListWithCapacity(10);
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("测试");
            user.setAge(18 + i);
            list.add(user);
        }
        return list;
    }
}

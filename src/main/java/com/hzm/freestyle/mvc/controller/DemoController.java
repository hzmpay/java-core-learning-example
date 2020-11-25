package com.hzm.freestyle.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月18日
 */
@RequestMapping
@RestController
public class DemoController {

    @RequestMapping("demo")
    public void demo() {
        System.out.println(LocalDateTime.now().toString());
    }

    @RequestMapping("demo2")
    public void demo2(String name, Integer age) {
        System.out.println(new StringJoiner(" ========= ").add("name = " + name).add("age = " + age).toString());
    }
}

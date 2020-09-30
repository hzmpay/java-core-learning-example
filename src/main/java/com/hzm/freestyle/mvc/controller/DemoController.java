package com.hzm.freestyle.mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
}

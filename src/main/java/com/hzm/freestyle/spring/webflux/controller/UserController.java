package com.hzm.freestyle.spring.webflux.controller;

import com.hzm.freestyle.spring.webflux.entity.User;
import com.hzm.freestyle.spring.webflux.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月11日
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/info")
    public Mono<User> info() {
        User userInfo = userService.getUserInfo();
        return Mono.just(userInfo);
    }

    @RequestMapping("/list")
    public Flux<User> list() {
        List<User> list = userService.list();
        return Flux.just(list.toArray(new User[list.size()]));
    }

    @RequestMapping("/list2")
    public Flux<User> list2() {
        User userInfo = userService.getUserInfo();
        return Flux.just(userInfo);
    }
}

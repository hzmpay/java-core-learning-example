package com.hzm.freestyle;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;

@SpringBootApplication
public class FreestyleApplication {

    @Resource
    private ServerProperties serverProperties;

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(FreestyleApplication.class, args);
        final ServerProperties.Tomcat tomcat = context.getBean(ServerProperties.class).getTomcat();
        final int maxThreads = tomcat.getMaxThreads();

        System.out.println("tomcat为" + JSONObject.toJSONString(tomcat));
        System.out.println("tomcat参数为" + JSONObject.toJSONString(maxThreads));


    }

}

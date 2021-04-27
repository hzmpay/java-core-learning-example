package com.hzm.freestyle.springboot.autoconfigure;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * springboot自动注入配置
 *
 * @author Hezeming
 * @version 1.0
 * @date 2021年04月27日
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(DemoServerProperties.class)
@Slf4j
public class DemoServerAutoConfigure {

    @Bean
    @ConditionalOnClass(HttpServer.class)
    public HttpServer httpServer(DemoServerProperties properties) throws IOException {
        final int port = properties.getPort();
        HttpServer httpServer = HttpsServer.create(new InetSocketAddress(port), 0);

        HttpHandler httpHandler = new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                String query = httpExchange.getRequestURI().getQuery();
                log.info("参数为：{}", query);
                httpExchange.getResponseBody().write("hello world!".getBytes());
            }
        };
        httpServer.createContext("/hello", httpHandler);
        httpServer.start();
        log.info("[httpServer][启动服务器成功，端口为:{}]", port);
        return httpServer;
    }
}

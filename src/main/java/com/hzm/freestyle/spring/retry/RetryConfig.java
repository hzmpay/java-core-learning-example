package com.hzm.freestyle.spring.retry;

import com.hzm.freestyle.util.SpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月07日
 */
@Configuration
@ComponentScan(value = {"com.hzm.freestyle.spring.retry"/*, "com.hzm.freestyle.util"*/})
@EnableRetry()
//@EnableRetry(proxyTargetClass = true)
//@EnableAspectJAutoProxy(exposeProxy = true)
public class RetryConfig {

    @Bean
    public SpringUtil springUtil() {
        return new SpringUtil();
    }

}

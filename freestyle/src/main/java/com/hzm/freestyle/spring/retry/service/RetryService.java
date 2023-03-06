package com.hzm.freestyle.spring.retry.service;

import com.alibaba.fastjson.JSONObject;
import com.hzm.freestyle.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月07日
 */
@Service
@Slf4j
public class RetryService {

    public void sayAopTest() {
//        ((RetryService) AopContext.currentProxy()).say();
        RetryService retryService = SpringUtil.getBean(RetryService.class);
//        System.out.println(retryService);
//        System.out.println(this);
//        System.out.println(this == retryService);
//        System.out.println(this.equals(retryService));
        retryService.say();
    }

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(value = 1000L), recover = "sayRecover")
    public String say() {
        RetryContext context = RetrySynchronizationManager.getContext();
        log.info("say开始执行任务啦 =============>");
        log.info("say重试原始参数为 ：{} ", context);
        log.info("say重试JSON参数为 ：{} ", JSONObject.toJSONString(context));
        if (1 == 1) {
            throw new IllegalArgumentException("say异常");
        }
        System.out.println("say 。。。。。。");
        return "OK";
    }

    @Recover
    public String sayRecover(Exception e) {
        log.info("失败的默认处理 :{} ", e.getMessage());
        return "失败的默认处理";
    }

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(value = 1000L), recover = "sayRecover")
    public String say2() {
        RetryContext context = RetrySynchronizationManager.getContext();
        log.info("say2开始执行任务啦 =============>");
        log.info("say2重试原始参数为 ：{} ", context);
        log.info("say2重试JSON参数为 ：{} ", JSONObject.toJSONString(context));
        if (1 == 1) {
            throw new IllegalArgumentException("say2异常");
        }
        System.out.println("say2 。。。。。。");
        return "OK";
    }
}

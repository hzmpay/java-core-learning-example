package com.hzm.freestyle.spring.retry;

import com.alibaba.fastjson.JSONObject;
import com.hzm.freestyle.spring.retry.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年05月07日
 */
@Slf4j
public class Application {

    public static void main(String[] args) throws Throwable {

//        retryForHardCoding();
        retryForAnnotation();

    }

    public static void retryForAnnotation() throws Throwable {
        // 2.注解方式
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RetryConfig.class);
        RetryService retryService = context.getBean(RetryService.class);
        for (int i = 0; i < 1; i++) {
            retryService.sayAopTest();
        }
    }


    /**
     * 硬编码的方式
     *
     * @param
     * @return void
     * @author Hezeming
     */
    public static void retryForHardCoding() {
        // 1.编码方式
        RetryTemplate retryTemplate = RetryTemplate.builder()
                // 重试3次
                .maxAttempts(3)
                // 固定重试时间
                .fixedBackoff(100)
                // 重试的异常
                .retryOn(Throwable.class)
                .build();
        // 重试的实现
        RetryCallback retryCallback = new RetryCallback<String, Throwable>() {
            @Override
            public String doWithRetry(RetryContext context) throws Throwable {
                log.info("开始执行任务啦 =============>");
                log.info("重试原始参数为 ：{} ", context);
                log.info("重试JSON参数为 ：{} ", JSONObject.toJSONString(context));
                throw new IllegalArgumentException();
//                if (1 == 1) {
//                    throw new IllegalArgumentException();
//                }
//                return "OK";
            }
        };
        // 重试机会用完之后的补偿回调
        RecoveryCallback<String> recoveryCallback = new RecoveryCallback<String>() {
            @Override
            public String recover(RetryContext context) throws Exception {
                //
                return "失败的默认处理";
            }
        };
//        String result = retryTemplate.execute(retryCallback);
        String result = retryTemplate.execute(retryCallback, recoveryCallback);
        log.info("返回结果 ：{} ", result);
    }
}

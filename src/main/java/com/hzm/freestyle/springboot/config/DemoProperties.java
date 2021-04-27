package com.hzm.freestyle.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年04月27日
 */
@Configuration
@ConfigurationProperties(prefix = "demo.properties")
@Data
public class DemoProperties {

    private String personName;
}

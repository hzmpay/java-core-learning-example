package com.hzm.freestyle.springboot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * springboot自动注入配置文件
 *
 * @author Hezeming
 * @version 1.0
 * @date 2021年04月27日
 */
@ConfigurationProperties(prefix = "demo.server")
@Data
public class DemoServerProperties {

    private int port;
}

package com.hzm.freestyle.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年01月28日
 */
@Configuration
public class ZookeeperConfig {

    /**
     * zookeeper connection string
     */
    private String zkConnString = "172.21.23.56:2181";
    /**
     * zookeeper session time out
     */
    private Integer zkSessionTimeout = 200 * 1000;

    @Bean
    public CuratorFramework createAndStartCuratorFramework() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(zkConnString)
                .sessionTimeoutMs(zkSessionTimeout)
                .retryPolicy(new RetryOneTime(1))
                .build();
        curatorFramework.start();
        return curatorFramework;
    }
}

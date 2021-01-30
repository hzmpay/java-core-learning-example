package com.hzm.freestyle.zookeeper;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年01月28日
 */
@Slf4j
public class ZookeeperTest {

    public static final CuratorFramework zkClient = initClient();

    public static void main(String[] args) throws Exception {
        watch2Test();
    }

    public static void watch2Test() throws Exception {
        String watcher = createNode("/watcher", "watcher111");
        log.info("创建节点成功 : {} ", watcher);


        int len = 5;
        String[] paths = new String[len];
        String parentName = "/watcher/";
        for (int i = 0; i < len; i++) {
            paths[i] = parentName + "watch" + i;
            createNode(paths[i], paths[i]);
            log.info("创建节点成功 : {} ", paths[i]);
        }

        watchMuch(new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                log.info("监听到数据改变 ：{} ", JSONObject.toJSONString(event));
            }
        }, paths);


        for (int i = 0; i < paths.length; i++) {
            String path = paths[i];
            log.info("{} 节点开始改变", path);
//            zkClient.getData().forPath(path);
            zkClient.setData().forPath(path, ("change" + System.currentTimeMillis()).getBytes());
            Thread.sleep(1000);
        }

        Thread.sleep(1000000);
    }

    public static void checkTest() throws Exception {
        Stat stat = zkClient.checkExists().forPath("/channelNodes/aliChannel");
        log.info(stat.toString());

        byte[] bytes = zkClient.getData().forPath("/channelNodes/aliChannel");
        log.info(new String(bytes));
    }

    public static void watch1Test() throws Exception {
        String watcher = createNode("/watcher", "watcher111");
        log.info("创建节点成功 : {} ", watcher);


        String path = "/watcher/demo1";
        String demo1 = createNode(path, "demo1");
        log.info("创建节点成功 : {} ", demo1);

        watchOne(event -> {
            log.info("监听到数据改变 ：{} ", JSONObject.toJSONString(event));
        }, path);

        Thread.sleep(1000);

        log.info("{} 节点开始改变", path);
        zkClient.setData().forPath(path, "demo2".getBytes());

        Thread.sleep(1000);

        log.info("{} 节点开始改变", path);
        zkClient.setData().forPath(path, "demo3".getBytes());

        Thread.sleep(1000000);
    }

    public static CuratorFramework initClient() {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ZookeeperConfig.class);
//        return context.getBean(CuratorFramework.class);
        // 重试连接等待时间
        int baseSleepTimeMs = 10 * 1000;
        // 重试次数
        int maxRetries = 10;
        // 重试间隔时间
        int maxSleepMs = 5 * 1000;
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("172.21.23.56:2181")
                .sessionTimeoutMs(200 * 1000)
                .retryPolicy(new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries, maxSleepMs))
                .build();
        curatorFramework.start();
        return curatorFramework;
    }

    public static String createNode(String path, String msg) throws Exception {
        return createNode(path, msg, CreateMode.PERSISTENT);
    }

    public static String createNode(String path, String msg, CreateMode createMode) throws Exception {
        Stat stat = zkClient.checkExists().forPath(path);
        if (stat == null) {
            return zkClient.create().withMode(createMode).forPath(path, msg.getBytes());
        }
        return path;
    }

    /**
     * 设置单个节点监听，只触发一次
     *
     * @param watcher
     * @param path
     * @return java.lang.String
     * @author Hezeming
     */
    public static String watchOne(Watcher watcher, String path) throws Exception {
        byte[] bytes = zkClient.getData().usingWatcher(watcher).forPath(path);
        String data = new String(bytes);
        log.info("设置监听后的节点数据为：{} ", data);
        return data;
    }

    /**
     * 设置多个节点监听，每个节点只触发一次
     *
     * @param curatorListener
     * @param paths
     * @return void
     * @author Hezeming
     */
    public static void watchMuch(CuratorListener curatorListener, String ... paths) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        zkClient.getCuratorListenable().addListener(curatorListener, executorService);

        for (String path : paths) {
            zkClient.getData().inBackground().forPath(path);
            log.info("设置监听后的节点数据为：{} ", path);
        }
    }

    public static void watch3(String path) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        PathChildrenCache pathChildrenCache = new PathChildrenCache(zkClient, path, true);

        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                ChildData data = event.getData();
                PathChildrenCacheEvent.Type type = event.getType();
            }
        });
    }
}

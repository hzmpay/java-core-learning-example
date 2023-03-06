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
import org.apache.zookeeper.WatchedEvent;
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

    public static final String PARENT_NODE = "/watcher";

    public static void main(String[] args) throws Exception {
        watchCycle1Test();
    }


    public static void watchCycle1Test() throws Exception {

        String[] paths = createTestNode(5);

        watchCycle1(PARENT_NODE);

        String path = paths[0];
        for (int i = 0; i < 3; i++) {
            log.info("{} 节点开始改变", path);
            zkClient.setData().forPath(path, ("改变" + i).getBytes());
            Thread.sleep(500);
        }

        Thread.sleep(1000000);

    }

    public static String[] createTestNode(int len) throws Exception {
        // 建立watcher父节点
        String watcher = createNode(PARENT_NODE, "watcher111");
        log.info("创建节点成功 : {} ", watcher);
        String[] paths = new String[len];
        String parentName = "/watcher/";
        for (int i = 0; i < len; i++) {
            paths[i] = parentName + "watch" + i;
            createNode(paths[i], paths[i]);
            log.info("创建节点成功 : {} ", paths[i]);
        }
        return paths;
    }

    public static void watch2Test() throws Exception {
        String watcher = createNode(PARENT_NODE, "watcher111");
        log.info("创建节点成功 : {} ", watcher);


        int len = 1;
        String[] paths = new String[len];
        String parentName = "/watcher/";
        for (int i = 0; i < len; i++) {
            paths[i] = parentName + "watch" + i;
            createNode(paths[i], paths[i]);
            log.info("创建节点成功 : {} ", paths[i]);
        }

        watchMuch(paths);

        String path = paths[0];
        log.info("{} 节点开始改变", path);
//        zkClient.setData().forPath(path, "改变22222".getBytes());
        zkClient.delete().forPath(path);

//        for (int i = 0; i < paths.length; i++) {
//            String path = paths[i];
//            log.info("{} 节点开始改变", path);
//            zkClient.setData().forPath(path, ("change" + System.currentTimeMillis()).getBytes());
//            Thread.sleep(1000);
//        }
//
//        for (int i = 0; i < paths.length; i++) {
//            String path = paths[i];
//            log.info("{} 节点开始改变", path);
//            zkClient.setData().forPath(path, ("change" + System.currentTimeMillis()).getBytes());
//            Thread.sleep(1000);
//        }

        Thread.sleep(1000000);
    }

    public static void checkTest() throws Exception {
        Stat stat = zkClient.checkExists().forPath("/channelNodes/aliChannel");
        log.info(stat.toString());

        byte[] bytes = zkClient.getData().forPath("/channelNodes/aliChannel");
        log.info(new String(bytes));
    }

    public static void watch1Test() throws Exception {
        String watcher = createNode(PARENT_NODE, "watcher111");
        log.info("创建节点成功 : {} ", watcher);


        String path = "/watcher/demo1";
        String demo1 = createNode(path, "demo1");
        log.info("创建节点成功 : {} ", demo1);

        watchOne(path);

        Thread.sleep(1000);

        log.info("{} 节点开始改变", path);
        zkClient.delete().forPath(path);

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
     * @param path
     * @return java.lang.String
     * @author Hezeming
     */
    public static String watchOne(String path) throws Exception {
        byte[] bytes = zkClient.getData().usingWatcher(new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                log.info("监听到数据改变 ：{} ", JSONObject.toJSONString(event));
            }
        }).forPath(path);
        String data = new String(bytes);
        log.info("设置监听后的节点数据为：{} ", data);
        return data;
    }

    /**
     * 设置多个节点监听，只触发一次，不管哪个节点改动都会触发所有的监听操作执行，
     * 比如：设置了A,B,C三个节点，此时每个节点有对应的监听
     *
     * @param paths
     * @return void
     * @author Hezeming
     */
    public static void watchMuch(String ... paths) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        zkClient.getCuratorListenable().addListener(new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                // TODO 有问题，这里用了delete，但是回调的type还是GET_DATA
                log.info("监听到数据改变 ：{} ", JSONObject.toJSONString(event));
            }
        }, executorService);

        for (String path : paths) {
            zkClient.getData().inBackground().forPath(path);
            log.info("设置监听后的节点数据为：{} ", path);
        }
    }

    /**
     * 循环监听指定节点下的孩子节点变更，第一次会触发所有的监听执行，后面会触发执行的监听执行
     *
     * @param path
     * @return void
     * @author Hezeming
     */
    public static void watchCycle1(String path) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        PathChildrenCache pathChildrenCache = new PathChildrenCache(zkClient, path, true);

        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                ChildData data = event.getData();
                PathChildrenCacheEvent.Type type = event.getType();
                switch (type) {
                    case CHILD_ADDED:
                        System.out.println("【监听到数据改变】 CHILD_ADDED : "+ data.getPath() +"  数据:"+ event);
                        break;
                    case CHILD_REMOVED:
                        System.out.println("【监听到数据改变】 CHILD_REMOVED : "+ data.getPath() +"  数据:"+ event);
                        break;
                    case CHILD_UPDATED:
                        System.out.println("【监听到数据改变】 CHILD_UPDATED : "+ data.getPath() +"  数据:"+ event);
                        break;
                    default:
                        break;
                }
            }
        }, executorService);

        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
    }
}

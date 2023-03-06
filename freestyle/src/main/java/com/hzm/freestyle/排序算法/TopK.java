package com.hzm.freestyle.排序算法;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hezeming
 * @version 1.0
 * @date 2022年07月22日
 */
public class TopK {

    public static void main(String[] args) {
        new TopK().demo();
    }

    private void demo() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
        System.out.println(lruCache);
    }

    class LRUCache {

        class Node {
            int key;
            int value;
            Node prev;
            Node next;

            public Node() {
            }

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private Map<Integer, Node> cache;
        private int capacity;
        private int size;
        // 虚拟头尾节点，减少空判断
        private Node head;
        private Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.cache = new HashMap<>();
            this.head = new Node();
            this.tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                // 不存在返回-1
                return -1;
            }
            // 存在需要做移动到head；
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);
            if (node != null) {
                // 已存在，覆盖值，移到head即可
                node.value = value;
                moveToHead(node);
                return;
            }
            // 不存在，进行添加处理
            node = new Node(key, value);
            cache.put(key, node);
            addHead(node);
            size++;
            // 是否达到阈值
            if (size > capacity) {
                // 达到阈值：剔除最后一个元素，添加到头节点
                Node removeNode = removeTail();
                cache.remove(removeNode);
                size--;
            }
        }

        private Node removeTail() {
            Node node = tail.prev;
            removeNode(node);
            return node;
        }

        private void addHead(Node node) {
            // 添加头节点
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addHead(node);
        }

        private void removeNode(Node node) {
            // 断开连接
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}

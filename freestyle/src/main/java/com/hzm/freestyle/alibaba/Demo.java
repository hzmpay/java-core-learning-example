package com.hzm.freestyle.alibaba;

/**
 * @author hezeming
 * @version 1.0
 * @date 2022年08月02日
 */
public class Demo {

    static class ListNode {
        ListNode next;
        int val;
        public ListNode(int val, ListNode next) {
            this.next = next;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, null)));
        System.out.println(demo(listNode, 3));
    }

    public static int demo(ListNode listNode, int k) {
        ListNode slowNode = listNode;
        ListNode quickNode = listNode;
        while (k-- > 0) {
            quickNode = quickNode.next;
        }
        while (quickNode != null) {
            quickNode = quickNode.next;
            slowNode = slowNode.next;
        }
        return slowNode.val;

    }
}

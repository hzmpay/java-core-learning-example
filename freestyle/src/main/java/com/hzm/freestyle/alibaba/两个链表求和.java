package com.hzm.freestyle.alibaba;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年12月16日
 */
public class 两个链表求和 {

    public static void main(String[] args) {
        ListNode listNode3 = new ListNode(4, null);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        System.out.println("=============");
        print(listNode1);

        ListNode listNode6 = new ListNode(6, null);
        ListNode listNode5 = new ListNode(5, listNode6);
        ListNode listNode4 = new ListNode(9, listNode5);
        System.out.println("=============");
        print(listNode4);

        boolean isAdd = false;
        ListNode node1 = reverse(listNode1);
        ListNode node2 = reverse(listNode4);

        ListNode result = calc(node1, node2, isAdd);

        ListNode cur = result;

        do {
            isAdd = cur.value >= 10;
            if (isAdd) {
                cur.value = cur.value - 10;
            }
            node1 = node1.next;
            node2 = node2.next;
            cur.next = calc(node1, node2, isAdd);
            cur = cur.next;
        } while (cur != null && result != cur);


        System.out.println("=============");
        result = reverse(result);
        if (isAdd) {
            result = new ListNode(1, result);
        }
        print(result);

    }

    public static ListNode calc(ListNode node1, ListNode node2, boolean isAdd) {
        // 递归
        if (node1 == null && node2 == null) {
            return null;
        } else if (node1 == null) {
            return node2;
        } else if (node2 == null) {
            return node1;
        }
        int value = node1.value + node2.value;
        if (isAdd) {
            value++;
        }
        node1.value = value;
        return node1;
    }

    public static void print(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.value);
            listNode = listNode.next;
        }
    }

    public static ListNode reverse(ListNode listNode) {
        ListNode cur = listNode;
        ListNode pre = null;

        while (cur != null) {

            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }

    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }
}

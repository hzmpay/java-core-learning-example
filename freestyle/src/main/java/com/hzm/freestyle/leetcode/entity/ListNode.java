package com.hzm.freestyle.leetcode.entity;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年11月09日
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static void main(String[] args) {
        final ListNode listNode3 = new ListNode(3, null);
        final ListNode listNode2 = new ListNode(4, listNode3);
        final ListNode listNode = new ListNode(2, listNode2);

        final ListNode listNode6 = new ListNode(4, null);
        final ListNode listNode5 = new ListNode(6, listNode6);
        final ListNode listNode4 = new ListNode(5, listNode5);

//        out(listNode);
//        out(listNode4);

        final ListNode listNodeA = new ListNode(9);
        ListNode curListNodeA = listNodeA;
        for (int i = 0; i < 7; i++) {
            curListNodeA.next = new ListNode(9);
            curListNodeA = curListNodeA.next;
        }

        final ListNode listNodeB = new ListNode(9);
        ListNode curListNodeB = listNodeB;
        for (int i = 0; i < 4; i++) {
            curListNodeB.next = new ListNode(9);
            curListNodeB = curListNodeB.next;
        }

        out(listNodeA);
        out(listNodeB);
//
        ListNode newListNode = addTwoNumbers(listNodeA, listNodeB);
//        ListNode newListNode = addTwoNumbers(listNode, listNode4);

        out(newListNode);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else {
            int calc = 0;
            ListNode result = null;
            ListNode cur = null;
            while (true) {
                boolean isL1Null = (l1 == null);
                boolean isL2Null = (l2 == null);
                if (isL1Null && isL2Null) {
                    if (calc > 0) {
                        // 最后一位还有进位则进1，多出一个节点
                        cur.next = new ListNode(1);
                    }
                    break;
                }
                int sum = 0;
                if (!isL1Null) {
                    sum += l1.val;
                }
                if (!isL2Null) {
                    sum += l2.val;
                }
                // 加进位
                sum += calc;
                // 生成余数(当前节点的值)和进位
                if (sum >= 10) {
                    sum = sum % 10;
                    calc = 1;
                } else {
                    calc = 0;
                }
                ListNode newListNode = new ListNode(sum);
                if (result == null) {
                    result = newListNode;
                } else {
                    cur.next = newListNode;
                }
                cur = newListNode;
                l1 = isL1Null ? null : l1.next;
                l2 = isL2Null ? null : l2.next;
            }
            return result;
        }
    }

    public static void out(ListNode listNode) {
        System.out.print(listNode.val);
        if (listNode.next != null) {
            System.out.print(" -> ");
            out(listNode.next);
        }
        System.out.println("");
    }


    public static void reverse(ListNode listNode) {
        ListNode cur = listNode;
        ListNode pre = null;
        while (cur != null) {
            // 取出当前节点的下一个节点
            ListNode next = cur.next;
            // 将前驱节点置为当前节点的下一个节点
            cur.next = pre;
            // 将当前节点置为下一个节点的前驱节点
            pre = cur;
            // 将下一个节点置为当前节点
            cur = next;
        }
    }
}

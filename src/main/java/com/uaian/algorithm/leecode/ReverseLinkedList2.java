package com.uaian.algorithm.leecode;

/**
 * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表
 *
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 */
public class ReverseLinkedList2 {
    public static void main(String[] args) {
        ListNode listNode5  = new ListNode(5, null);
        ListNode listNode4  = new ListNode(4, listNode5);
        ListNode listNode3  = new ListNode(3, listNode4);
        ListNode listNode2  = new ListNode(2, listNode3);
        ListNode listNode1  = new ListNode(1, listNode2);
        reverseBetween(listNode1, 2, 4);
    }

    private static ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

//    public static ListNode reverseBetween(ListNode head, int left, int right) {
//        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
//        ListNode dummyNode = new ListNode(-1);
//        dummyNode.next = head;
//
//        ListNode pre = dummyNode;
//        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
//        // 建议写在 for 循环里，语义清晰
//        for (int i = 0; i < left - 1; i++) {
//            pre = pre.next;
//        }
//
//        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
//        ListNode rightNode = pre;
//        for (int i = 0; i < right - left + 1; i++) {
//            rightNode = rightNode.next;
//        }
//
//        // 第 3 步：切断出一个子链表（截取链表）
//        ListNode leftNode = pre.next;
//        ListNode curr = rightNode.next;
//
//        // 注意：切断链接
//        pre.next = null;
//        rightNode.next = null;
//
//        // 第 4 步：同第 206 题，反转链表的子区间
//        reverseLinkedList(leftNode);
//
//        // 第 5 步：接回到原来的链表中
//        pre.next = rightNode;
//        leftNode.next = curr;
//        return dummyNode.next;
//    }
//
//    private static void reverseLinkedList(ListNode head) {
//        // 也可以使用递归反转一个链表
//        ListNode pre = null;
//        ListNode cur = head;
//
//        while (cur != null) {
//            ListNode next = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//        }
//    }
}

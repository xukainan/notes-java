package com.uaian.algorithm.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * 环形链表
 *
 * 给你一个链表的头节点 head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点
 */
public class LinkedListCycle2 {
    public static void main(String[] args) {
        ListNode listNode4  = new ListNode(4, null);
        ListNode listNode3  = new ListNode(0, listNode4);
        ListNode listNode2  = new ListNode(2, listNode3);
        ListNode listNode1  = new ListNode(3, listNode2);
        listNode4.next = listNode2;
        ListNode cycleNode  = detectCycle(listNode1);
        System.out.println(cycleNode);
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
}

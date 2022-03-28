package com.uaian.algorithm.leecode;

import java.util.HashMap;

/**
 * 环形链表
 *
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        ListNode listNode4  = new ListNode(4, null);
        ListNode listNode3  = new ListNode(0, listNode4);
        ListNode listNode2  = new ListNode(5, listNode3);
        ListNode listNode1  = new ListNode(3, listNode2);
        listNode4.next = listNode2;
        boolean flag  = hasCycle(listNode1);
        System.out.println(flag);
    }

    public static boolean hasCycle(ListNode head) {
//        HashMap<ListNode, Integer> map = new HashMap<>();
//        ListNode cursor = head;
//        while (cursor != null) {
//           if(map.get(cursor) != null) {
//               return true;
//           }else {
//               map.put(cursor, cursor.val);
//           }
//            cursor = cursor.next;
//        }
//        return false;
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;

    }
}

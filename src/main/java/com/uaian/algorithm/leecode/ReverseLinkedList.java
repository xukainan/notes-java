package com.uaian.algorithm.leecode;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 */
class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode listNode5  = new ListNode(5, null);
        ListNode listNode4  = new ListNode(4, listNode5);
        ListNode listNode3  = new ListNode(3, listNode4);
        ListNode listNode2  = new ListNode(2, listNode3);
        ListNode listNode1  = new ListNode(1, listNode2);
        reverseList(listNode1);

    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}


class ListNode {
    int val;
    ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
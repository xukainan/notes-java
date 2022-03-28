package com.uaian.algorithm.leecode;

/**
 * 合并两个有序链表
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 */

public class MergeTwoSortedLists {
    public static void main(String[] args) {
//        ListNode listNode6  = new ListNode(4, null);
        ListNode listNode5  = new ListNode(7, null);
        ListNode listNode4  = new ListNode(5, listNode5);

//        ListNode listNode3  = new ListNode(4, null);
        ListNode listNode2  = new ListNode(3, null);
        ListNode listNode1  = new ListNode(-9, listNode2);

        ListNode res = mergeTwoSortedLists(listNode1, listNode4);
    }

    private static ListNode mergeTwoSortedLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode cursor = head;
        while (list1 != null && list2 != null){
            if(list1.val == list2.val) {
                ListNode tmpNode1 = new ListNode(list1.val, null);
                ListNode tmpNode2 = new ListNode(list2.val, tmpNode1);
                cursor.next = tmpNode2;
                list1 = list1.next;
                list2 = list2.next;
                cursor = cursor.next.next;
            }else if(list1.val < list2.val) {
                ListNode tmpNode1 = new ListNode(list1.val, null);
                cursor.next = tmpNode1;
                list1 = list1.next;
                cursor = cursor.next;
            }else {
                ListNode tmpNode2 = new ListNode(list2.val, null);
                cursor.next = tmpNode2;
                list2 = list2.next;
                cursor = cursor.next;
            }
        }

        while (list1 != null) {
            ListNode tmpNode1 = new ListNode(list1.val, null);
            cursor.next = tmpNode1;
            list1 = list1.next;
            cursor = cursor.next;
        }

        while (list2 != null) {
            ListNode tmpNode2 = new ListNode(list2.val, null);
            cursor.next = tmpNode2;
            list2 = list2.next;
            cursor = cursor.next;
        }


        return head.next;
    }
}

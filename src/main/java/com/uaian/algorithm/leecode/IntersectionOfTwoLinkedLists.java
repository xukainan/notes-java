package com.uaian.algorithm.leecode;

import java.util.HashMap;
import java.util.Map;

public class IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {

        ListNode listNode2  = new ListNode(5, null);
        ListNode listNode1  = new ListNode(1, listNode2);

        ListNode listNode8  = new ListNode(4, null);
        ListNode listNode7  = new ListNode(6, listNode8);
        ListNode listNode6  = new ListNode(2, listNode7);

        ListNode intersection = getIntersectionNode(listNode1, listNode6);
        System.out.println(intersection);
    }


    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        Map<ListNode, Integer> map = new HashMap<>();
//        ListNode tmpNode = headA;
//        while (tmpNode != null){
//            map.put(tmpNode, tmpNode.val);
//            tmpNode  = tmpNode.next;
//        }
//        tmpNode = headB;
//        while (tmpNode != null){
//            if(map.get(tmpNode) != null){
//                return tmpNode;
//            }
//            tmpNode  = tmpNode.next;
//        }
//        return null;
        ListNode cursor1 = headA;
        ListNode cursor2 = headB;
        if(cursor1 == null || cursor2 == null){
            return null;
        }
        while (cursor1 != cursor2) {
            cursor1 = cursor1 == null?headB: cursor1.next;
            cursor2 = cursor2 == null?headA: cursor2.next;
        }
        return cursor1;
    }
}

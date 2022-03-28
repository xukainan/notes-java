package com.uaian.algorithm.leecode;

public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode listNode6  = new ListNode(9, null);
        ListNode listNode5  = new ListNode(9, listNode6);
        ListNode listNode4  = new ListNode(9, listNode5);

        ListNode listNode7  = new ListNode(9, null);
        ListNode listNode8  = new ListNode(9, listNode7);
        ListNode listNode9  = new ListNode(9, listNode8);
        ListNode listNode3  = new ListNode(9, listNode9);
        ListNode listNode2  = new ListNode(9, listNode3);
        ListNode listNode1  = new ListNode(9, listNode2);
        ListNode listNode = addTwoNumbers(listNode1, listNode4, 0);
        System.out.println(listNode);
    }


    public static ListNode addTwoNumbers(ListNode first, ListNode second, int carry) {
        int sum = 0;
        int num_first = 0;
        int num_second = 0;
        ListNode firstNext = null;
        ListNode secondNext = null;

        if(first == null && second == null && carry == 0){
            return null;
        }

        if(first != null) {
            num_first = first.val;
            firstNext = first.next;
        }
        if(second != null) {
            num_second = second.val;
            secondNext = second.next;
        }
        if(num_first + num_second + carry >= 10){
            sum = num_first + num_second + carry - 10;
            carry = 1;
        }else {
            sum = num_first + num_second + carry;
            carry = 0;
        }

        return new ListNode(sum, addTwoNumbers(firstNext, secondNext, carry));
    }
}



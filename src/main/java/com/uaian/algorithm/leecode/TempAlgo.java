package com.uaian.algorithm.leecode;


import com.uaian.algorithm.leecode.ListNode;

import java.util.*;

public class TempAlgo {
    public static void main(String[] args) {
        // [3,9,20,6,10,15,7]
        TreeNode treeNode7 = new TreeNode(10);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(7);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode3 = new TreeNode(20, treeNode4, treeNode5);
        TreeNode treeNode2 = new TreeNode(9, treeNode6, treeNode7);
        TreeNode treeNode1 = new TreeNode(3, treeNode2, treeNode3);
        List<List<Integer>> lists = zigzagLevelOrder(treeNode1);
        //[3] [20, 9] [6, 10, 15, 7]
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();

        if(root == null){
            return lists;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        Boolean rightStartFlag = true;

        while (!deque.isEmpty()){
            List<Integer> list = new ArrayList<>();
            TreeNode poll = deque.poll();
            list.add(poll.val);
            if(rightStartFlag){
                deque.offerFirst(poll.left);
                deque.offerFirst(poll.right);
            }else {
                deque.offerLast(poll.left);
                deque.offerLast(poll.right);
            }

        }


        return lists;
    }




}

package com.uaian.algorithm.leecode;

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {
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
        List<List<Integer>> lists = new LinkedList<>();

        if(root == null) {
            return lists;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isOrderLeft = true;

        while (!queue.isEmpty()) {
            Deque<Integer> deque = new LinkedList<>();
            int size = queue.size();  //一定要单独写
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if(isOrderLeft) {
                    deque.offerLast(poll.val);
                }else {
                    deque.offerFirst(poll.val);
                }
                if(poll.left != null) {
                    queue.offer(poll.left);
                }
                if(poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            lists.add(new LinkedList<Integer>(deque));
            isOrderLeft = !isOrderLeft;
        }

        return lists;
    }


//    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//        List<List<Integer>> lists = new ArrayList<>();
//        int level = 0;
//        zigzagLevelOrderMethod(lists, level, root);
//        return lists;
//    }


//    private static void zigzagLevelOrderMethod(List<List<Integer>> lists, int level, TreeNode root) {
//
//        if(root == null)
//            return;
//
//
//        if(lists.size() == level)
//            lists.add(new ArrayList<>());
//
//        if (level % 2 == 1){
//            lists.get(level).add(0, root.val);
//        } else {
//            lists.get(level).add(root.val);
//        }
//
//
//        zigzagLevelOrderMethod(lists, level + 1, root.left);
//        zigzagLevelOrderMethod(lists, level + 1, root.right);
//
//    }
}

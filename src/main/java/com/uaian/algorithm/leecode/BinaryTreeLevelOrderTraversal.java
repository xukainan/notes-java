package com.uaian.algorithm.leecode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 */
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        //[1,2,null,3,null,4,null,5]
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(4, treeNode5, null);
        TreeNode treeNode3 = new TreeNode(3, treeNode4, null);
        TreeNode treeNode2 = new TreeNode(2, treeNode3, null);
        TreeNode treeNode1 = new TreeNode(1, treeNode2, null);
        levelOrder(treeNode1);
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if(root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {

            int n  = queue.size();
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if(poll.left != null)
                    queue.add(poll.left);
                if(poll.right != null)
                    queue.add(poll.right);
            }
            lists.add(list);

        }



        return lists;
    }

//    private static List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> lists = new ArrayList<>();
//
//        if(root == null) {
//            return lists;
//        }
//
//        levelOrderMethod(root, lists, 0);
//
//
//        return lists;
//    }
//
//    private static void levelOrderMethod(TreeNode root, List<List<Integer>> lists, int level) {
//        if(level == lists.size())
//            lists.add(new ArrayList<>());
//
//        lists.get(level).add(root.val);
//
//        if(root.left != null)
//            levelOrderMethod(root.left, lists, level + 1);
//
//        if(root.right != null)
//            levelOrderMethod(root.right, lists, level +1);
//    }


}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

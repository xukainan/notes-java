package com.uaian.algorithm.leecode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 *
 */
public class LowestCommonAncestorOfABinaryTree {

    private static TreeNode ans;

    public static void main(String[] args) {
        //[1,2,null,3,null,4,null,5]
        TreeNode treeNode9 = new TreeNode(4, null, null);
        TreeNode treeNode8 = new TreeNode(7, null, null);
        TreeNode treeNode7 = new TreeNode(8, null, null);
        TreeNode treeNode6 = new TreeNode(0, null, null);
        TreeNode treeNode5 = new TreeNode(2, treeNode8, treeNode9);
        TreeNode treeNode4 = new TreeNode(6, null, null);
        TreeNode treeNode3 = new TreeNode(1, treeNode6, treeNode7);
        TreeNode treeNode2 = new TreeNode(5, treeNode4, treeNode5);
        TreeNode treeNode1 = new TreeNode(3, treeNode2, treeNode3);
        lowestCommonAncestor(treeNode1, treeNode2, treeNode9);
    }

    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> visited = new HashSet<Integer>();
        loop(root, map);
        while (p != null) {
            visited.add(p.val);
            p = map.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = map.get(q.val);
        }
        return null;
    }

    private static void loop(TreeNode root, Map<Integer, TreeNode> map) {
        if(root.left != null){
            map.put(root.left.val, root);
            loop(root.left, map);
        }
        if(root.right != null){
            map.put(root.right.val, root);
            loop(root.right, map);
        }
    }

//    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        getCommonTop(root, p , q);
//        return ans;
//    }
//
//    private static boolean getCommonTop(TreeNode root, TreeNode p, TreeNode q) {
//        if(root == null)
//            return false;
//
//        boolean leftFlag = getCommonTop(root.left, p, q);
//        boolean rightFlag = getCommonTop(root.right, p, q);
//
//
//        if(leftFlag == true && rightFlag == true)
//            ans = root;
//
//        if((root == p || root == q) && (leftFlag == true || rightFlag == true))
//            ans = root;
//
//        return (root == p || root == q || leftFlag == true || rightFlag == true);
//    }
}

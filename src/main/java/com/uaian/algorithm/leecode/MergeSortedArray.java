package com.uaian.algorithm.leecode;

import java.util.Stack;

/**
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4,5,6,0,0,0};
        int[] nums2 = new int[]{1,2,3};
        int m = 3;
        int n = 3;
        merge(nums1, m, nums2, n);
    }

    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m--+--n;

        while(n>=0) {
            nums1[i--] = m>=0 && nums1[m]>nums2[n] ? nums1[m--] : nums2[n--];
        }
    }

//    private static void merge(int[] nums1, int m, int[] nums2, int n) {
//        Stack<Integer> stack = new Stack<>();
//        int start1 = 0, start2 = 0;
//        while (start1 < m && start2 < n){
//            if(nums1[start1] > nums2[start2]){
//                stack.push(nums2[start2]);
//                start2++;
//            }else {
//                stack.push(nums1[start1]);
//                start1++;
//            }
//        }
//
//        while (start1 < m){
//            stack.push(nums1[start1++]);
//        }
//        while (start2 < n) {
//            stack.push(nums2[start2++]);
//        }
//
//        int index = nums1.length - 1;
//        while (!stack.isEmpty()){
//            nums1[index--] = stack.pop();
//        }
//
//    }

//    public static void merge(int[] nums1, int m, int[] nums2, int n) {
//        int start1 = 0, start2 = 0;
//        while (start1 < m && start2 < n){
//            if(nums1[start1] > nums2[start2]){
//                int tmp = nums1[start1];
//                nums1[start1] = nums2[start2];
//                int tmpIndex = start2;
//                while ((tmpIndex+1) < n && tmp > nums2[tmpIndex+1]) {
//                    nums2[tmpIndex] = nums2[tmpIndex+1];
//                    tmpIndex++;
//                }
//                nums2[tmpIndex] = tmp;
//            }
//            start1++;
//        }
//        while (start2 < n){
//            nums1[start1++] = nums2[start2++];
//        }
//    }
}

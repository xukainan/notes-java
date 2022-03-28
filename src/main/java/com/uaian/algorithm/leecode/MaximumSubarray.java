package com.uaian.algorithm.leecode;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{-2,-5};
        int maxSum = MaximumSubarrayDetail(nums);
        System.out.println(maxSum);
    }

    private static int MaximumSubarrayDetail(int[] nums) {
        int maxSum = nums[0];
        int tmpMax = 0;
        for (int i = 0; i < nums.length; i++) {
            if(tmpMax >= 0) {
                tmpMax = tmpMax + nums[i];
            }else {
                tmpMax = nums[i];
            }
            maxSum = Math.max(maxSum, tmpMax);
        }
        return maxSum;
    }

}

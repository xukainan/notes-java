package com.uaian.algorithm.leecode;

import scala.Int;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 *输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> lists = threeSum(nums);
        lists.stream().forEach(arr -> {
            arr.stream().forEach(item -> {
                System.out.print(item);
            });
        });
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        quickSort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int start = i + 1, end = nums.length - 1;
            if(i > 0 && cur == nums[i-1]){
                continue;
            }
            while (start < end) {
                if(cur + nums[start] + nums[end] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(cur);
                    list.add(nums[start]);
                    list.add(nums[end]);
                    lists.add(list);
                    while (start < end && nums[start] == nums[start+1]) {
                        start++;
                    }
                    start++;
                    while (start < end && nums[end] == nums[end-1]) {
                        end--;
                    }
                    end--;
                }else if(cur + nums[start] + nums[end] > 0) {
                    end--;
                }else {
                    start++;
                }
            }

        }
        return lists;
    }

    private static void quickSort(int[] nums, int start, int end) {
        int i, j, standard;

        if(start > end) {
            return;
        }

        i = start;
        j = end;
        standard = nums[start];

        while (start < end) {
            while (start < end && nums[end] >= standard) {
                end--;
            }
            nums[start] = nums[end];
            while (start < end && nums[start] <= standard){
                start++;
            }
            nums[end] = nums[start];
        }
        nums[end] = standard;

        quickSort(nums, i, end -1);
        quickSort(nums, end + 1, j);

    }
}

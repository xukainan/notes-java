package com.uaian.algorithm.sort;

/**
 * 桶排序
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 13, 4, 3, 8, 5, 10, 13, 4, 2, 6};
        int[] sorted_arr = bucketSortMethod(arr);
        for (int i = 0; i < sorted_arr.length; i++) {
            int sorted_val = sorted_arr[i];
            if(sorted_val > 0) {
                for (int j = 0; j < sorted_val; j++) {
                    System.out.println(i);
                }
            }
        }
    }

    private static int[] bucketSortMethod(int[] arr) {
        //准备一个覆盖待排序数组最小到最大的桶
        int[] tmp_arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            int arr_val = arr[i];
            int tmp_cur_val = tmp_arr[arr_val];
            //桶的对应位置上放待排序数的数量
            tmp_arr[arr_val] = ++tmp_cur_val;
        }
        return tmp_arr;
    }
}

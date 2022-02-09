package com.uaian.algorithm.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 13, 4, 3, 8, 5, 10, 13, 4, 2, 6};
        int[] sorted_arr = bubbleSortMethod(arr);
        for (int i = 0; i < sorted_arr.length; i++) {
            System.out.println(sorted_arr[i]);
        }
    }

    private static int[] bubbleSortMethod(int[] arr) {
        //第一遍循环:待冒泡的数，一直到倒数第二个数
        for (int i = 0; i < arr.length -1; i++) {
            //第二遍循环：冒泡的数和后面所有的数对比，如果小于后面的数，则交换位置
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] < arr[j]) {
                    arr[i] ^= arr[j];
                    arr[j] ^= arr[i];
                    arr[i] ^= arr[j];
                }
            }
        }
        return arr;
    }
}

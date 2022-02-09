package com.uaian.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 13, 4, 3, 8, 5, 10, 13, 4, 2, 6};
        quickSortMethod(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void quickSortMethod(int[] arr, int start, int end) {
        int i, j, standard;
        //start > end 结束所有递归循环
        if(start > end){
            return;
        }

        //i, j 用来传递给递归方法开始和结束，因为如果使用start,end。（start,end）在执行完第一轮递归以后值会改变，导致第二轮不准确
        i = start;
        j = end;
        standard = arr[start];

        //start > end 一个基准数的比较已经完成
        while (start < end) {
            //必须从右往左
            //首先从基准数开始， 从右往左找到一个数，与基准数交换
            //必须<= 否则遇到相同的数就死循环了
            while (arr[end] <= standard && start < end) {
                end--;
            }
            arr[start] = arr[end];
            //从左往右找到符合条件的数，放到end上，之前end的数已经放到start上
            while (arr[start] >= standard && start < end) {
                start++;
            }
            arr[end] = arr[start];
        }

        //最后把基准数放到中间位置
        arr[start] = standard;

        quickSortMethod(arr, i, start -1);
        quickSortMethod(arr, start +1, j);



    }


}

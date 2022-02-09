package com.uaian.algorithm.sort;

/**
 * 归并排序
 *
 * 将未排序的列表划分为n个子列表，每个子列表包含一个元素（一个元素的列表被认为是排序的）。
 * 反复合并子列表以产生新的排序子列表，直到只剩一个子列表。这将是排序列表。
 *
 * O(n·logn)
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 13, 4, 3, 8, 5, 10, 13, 4, 2, 6};
        mergeSortMethod(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void mergeSortMethod(int[] arr, int left, int right) {
        //递归到只有一个数的时候return
        if(left >= right)
            return;
        int mid = (right + left) / 2;
        mergeSortMethod(arr, left, mid);
        mergeSortMethod(arr, mid + 1, right);

        merge(arr, left, mid, right);

    }

    private static void merge(int[] data, int left, int mid, int right) {
        int[] tmpArr = new int[data.length];
        //第二组的开始位置
        int second_start = mid + 1;
        //temArr的游标
        int tem_index = left;
        int tmp_left = left;
        //循环，直到两组中的一组排序完成，剩余的肯定是最大的
        while (left <= mid && second_start <= right) {
            // 从两个数组中取出最小的放入临时数组
            if (data[left] <= data[second_start]) {
                tmpArr[tem_index++] = data[left++];
            } else {
                tmpArr[tem_index++] = data[second_start++];
            }
        }
        // 剩余部分依次放入临时数组
        while (second_start <= right) {
            tmpArr[tem_index++] = data[second_start++];
        }
        while (left <= mid) {
            tmpArr[tem_index++] = data[left++];
        }
        // 将临时数组中的内容拷贝回原数组中
        while (tmp_left <= right) {
            data[tmp_left] = tmpArr[tmp_left++];
        }
    }
}

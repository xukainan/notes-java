package com.uaian.algorithm.sort;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin实现归并排序
 */

public class ForkJoinMergeSort {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = new int[]{7, 1, 13, 4, 3, 8, 5, 10, 13, 4, 2, 6};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask task = new ForkJoinTask(arr, 0, arr.length-1);
        Future<Void> result = forkJoinPool.submit(task);
        result.get();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


    static class ForkJoinTask extends RecursiveTask<Void> {

        private int[] arr;

        private int start;

        private int end;

        public ForkJoinTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Void compute() {
            if(start >= end) {
                int mid = (start+end)/2;
                merge(arr, start, mid, end);
            }else {
                int mid = (start + end)/2;
                ForkJoinTask subTask1 = new ForkJoinTask(arr, start, mid);
                subTask1.fork();
                ForkJoinTask subTask2 = new ForkJoinTask(arr, mid + 1, end);
                subTask2.fork();
                subTask1.join();
                subTask2.join();
                merge(arr, start, mid, end);
            }
            return null;
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
}

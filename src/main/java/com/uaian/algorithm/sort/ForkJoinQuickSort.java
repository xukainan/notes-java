package com.uaian.algorithm.sort;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinQuickSort {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = new int[]{7, 1, 13, 4, 3, 8, 5, 10, 13, 4, 2, 6};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask task = new ForkJoinTask(arr, 0, arr.length - 1);
        java.util.concurrent.ForkJoinTask<Void> submit = forkJoinPool.submit(task);
        submit.get();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    static class ForkJoinTask extends RecursiveTask<Void> {

        int[] arr;

        int start;

        int end;

        public ForkJoinTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Void compute() {
            int pivot = partition(arr, start, end);
            ForkJoinTask task1 = null;
            ForkJoinTask task2 = null;
            if (pivot - start > 1) {
                task1 = new ForkJoinTask(arr, start, pivot-1);
                task1.fork();
            }
            if (end - pivot > 1) {
                task2 = new ForkJoinTask(arr, pivot+1, end);
                task2.fork();
            }
            if (task1 != null && !task1.isDone()) {
                task1.join();
            }
            if (task2 != null && !task2.isDone()) {
                task2.join();
            }
            return null;
        }

        public static int partition(int[] a, int left, int right) {
            int pivot = a[left];
            while (left < right) {
                while (left < right && a[right] >= pivot) {
                    right--;
                }
                swap(a, left, right);
                while (left < right && a[left] <= pivot) {
                    left++;
                }
                swap(a, left, right);
            }
            return left;
        }

        public static void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

    }
}

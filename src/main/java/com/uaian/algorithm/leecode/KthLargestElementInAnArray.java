package com.uaian.algorithm.leecode;

public class KthLargestElementInAnArray {

    public static void main(String[] args) {
        int[] arr = new int[]{3,2,3,1,2,4,5,5,6};
        int k = 4;
        quickSort(arr, 0, arr.length-1);
        System.out.println(arr[k-1]);
    }

    private static void quickSort(int[] arr, int start, int end) {
        int i,j,standard;

        if(start > end) {
            return;
        }

        i = start;
        j = end;
        standard = arr[start];

        while (start < end) {
            while (arr[end] <= standard && start < end) {
                end--;
            }
            arr[start] = arr[end];
            while (arr[start] >= standard && start < end){
                start++;
            }
            arr[end] = arr[start];
        }

        arr[end] = standard;

        quickSort(arr, i, end - 1);
        quickSort(arr, end + 1, j);


    }
}

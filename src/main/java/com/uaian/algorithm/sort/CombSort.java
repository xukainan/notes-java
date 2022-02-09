package com.uaian.algorithm.sort;

/**
 * 梳排序
 *
 * O(n·logn)
 */
public class CombSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 13, 4, 3, 8, 5, 10, 13, 4, 2, 6, 7};
        combSortMethod(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void combSortMethod(int[] arr) {
        int compLen = arr.length;
        double standard = 1.3d;
        boolean swapped = true ;


        //swapped 为了处理相同的元素 比如 776 第一次交换 7-6-7 还是不对
        while (compLen > 1 || swapped){
            if(compLen > 1){
                compLen = (int) (compLen / standard);
            }
            swapped = false ;
            for (int i = 0; i+compLen< arr.length; i++) {
                int compPoi = i + compLen;
                if(arr[i] > arr[compPoi]){
                    arr[i] ^= arr[compPoi];
                    arr[compPoi] ^= arr[i];
                    arr[i] ^= arr[compPoi];
                    swapped = true;
                }
            }
        }
    }
}

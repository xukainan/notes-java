package com.uaian.algorithm.sort;

/**
 * 插入排序
 * 将一个数插入一个已经排好序的数据中。
 * 第一次循环时，从第2个数开始处理。我们将第1个数作为已经排好序的数据：当第2个数 > 第1个数时，将第2个数放在第1个数后面一个位置；否则，将第2个数放在第1个数前面。此时，前两个数形成了一个有序的数据。
 * 第二次循环时，我们处理第3个数。此时，前两个数形成了一个有序的数据：首先比较第3个数和第2个数，当第3个数 > 第2个数时，将第3个数放在第2个数后面一个位置并结束此次循环；否则，再和第1个数比较。
 * 如果第3个数 > 第1个数，则将第3个数插入第1个数和第2个数中间；否则，第3个数 < 第1个数，则将第3个数放在第1个数前面。此时，前三个数形成了一个有序的数据。后续的数据同理处理，直至结束。
 *
 * 在最坏的情况下，即整个数组是倒序的，比较次数 = 1 + 2 + 3 + ... + (n - 2) + (n - 1) = n * (n - 1) / 2，此时的时间复杂度为：O(n^2)。在最好的情况下，即整个数组是正序的，比较次数 = n - 1，此时的时间复杂度为：O(n)。
 *
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 13, 4, 3, 8, 5, 10, 13, 4, 2, 6, 7};
        insertSortMethod(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void insertSortMethod(int[] arr) {
        for (int index = 1; index < arr.length; index++) {  //循环待排序的数
            int tmp_index = index; //排序的数交换后下标会变，所以临时变量
            for (int sorted = index - 1; sorted >= 0 ; sorted--) { //循环和已经排序好的数组比较
                if(arr[tmp_index] < arr[sorted]) { //如果小于则交换
                    arr[tmp_index] ^= arr[sorted];
                    arr[sorted] ^= arr[tmp_index];
                    arr[tmp_index] ^= arr[sorted];
                    tmp_index--; //交换后下标减小
                }else {
                    break;
                }
            }
        }
    }
}

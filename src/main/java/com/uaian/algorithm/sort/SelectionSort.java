package com.uaian.algorithm.sort;

/**
 * 选择排序
 * 每一次从待排序的数据元素中选出最小的一个元素，存放在序列的起始位置，直到全部待排序的数据元素排完。
 *
 * 第一次循环时，i = 0，首先将代表着“最小值下标”的minIndex赋值为第一个数字的数组下标，即minIndex = 0。然后从第2个元素开始与array[minIndex]进行比较，如果有比array[minIndex]小的数，
 * 则将minIndex赋值为这个数的下标。当所有元素比较完后，再将array[0]与array[minIndex]交换位置，则arary[0]的值即为最小的数。
 * 第二次循环时，从array[1]开始处理，最后比较出最小的数与array[1]交换位置，过程同步骤1。
 * 直至处理完倒数第二个，结束处理。
 *
 *
 * 如果将数组的长度看作n，第一次循环时，j 从1开始直到n - 1，因此比较次数为n - 1；第二次为n - 2，依此类推。最终得到选择排序的比较次数 = (n - 1) + (n - 2) + ... + 2 + 1 = n * (n - 1) / 2，因此时间复杂度为：O(n^2)。
 * 与冒泡排序的区别：选择排序指交换两个元素时并不影响其他元素的相邻位置，而冒泡排序在交换过程中会改变其他元素的相邻位置。
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 13, 4, 3, 8, 5, 10, 13, 4, 2, 6, 7};
        SelectionSortMethod(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void SelectionSortMethod(int[] arr) {
        for (int minimum = 0; minimum < arr.length - 1; minimum++) {  //循环当前数下标
            int min = minimum + 1; //将待排序数组的最小值暂时赋给第二个值
            for (int last = min + 1; last < arr.length; last++) { //找到待排序数组的最小值赋值给min
                if(arr[min] > arr[last]) {
                    min = last;
                }
            }
            if(arr[minimum] > arr[min]){ //如果当前数大于待排序的最小数，则交换
                arr[minimum] ^= arr[min];
                arr[min] ^= arr[minimum];
                arr[minimum] ^= arr[min];
            }
        }
    }
}

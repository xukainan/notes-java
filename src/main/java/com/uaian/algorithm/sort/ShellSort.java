package com.uaian.algorithm.sort;

/**
 * 希尔排序(Shell's Sort)是插入排序的一种又称“缩小增量排序”，是直接插入排序算法的一种更高效的改进版本。希尔排序是非稳定排序算法。
 *
 * 希尔排序在排序前：将一个序列分成了好几个序列在第一趟排序时：将这几个序列做插入排序。
 * 排序后，部分较大的数字往后靠，部分较小的数字往前靠在第二趟排序时：将这个序列又分了好几个序列做插入排序(但比第一次分的数要少,ps:如果第一次分5个，第二次可能就2个了)。
 * 排序后，部分较大的数字往后靠，部分较小的数字往前靠................
 * 在第n趟排序时：将这个序列又分了好几个序列(直到剩下一个序列)，从宏观上看，此序列就基本是有序的了。这时就用简单插入排序将数列直至已序
 *
 * O(n^1.3)到O(n^2.0)
 *

 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 13, 4, 3, 8, 5, 10, 13, 4, 2, 6, 7};
        ShellSortMethod(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void ShellSortMethod(int[] arrays) {
        //增量每次除以2
        for (int step = arrays.length / 2; step > 0 ; step /= 2) {
            //从增量那组开始往回插入排序
            for (int i = step; i < arrays.length; i++) {
                int j = i;
                int temp = arrays[j];
                while (j - step >= 0 && arrays[j - step] > temp) {
                    arrays[j] = arrays[j -step];
                    j = j-step;
                }
                arrays[j] = temp;
            }

        }
    }
}

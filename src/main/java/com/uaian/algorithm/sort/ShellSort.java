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
 * 现在我们有一个数组，该数组有6个元素int[] arrays = {2, 5, 1, 3, 4, 6};
 * 排序前：将该数组看成三个（arrays.length/2)数组，分别是:{2,3},{5,4},{1,6}
 * 第一趟排序：对三个数组分别进行插入排序，因此我们三个数组得到的结果为{2,3},{4,5},{1,6}
 * 此时数组是这样子的：{2, 4, 1, 3, 5, 6}
 * 第二趟排序：增量减少了，上面增量是3，此时增量应该为1了，
 * 因此把{2, 4, 1, 3, 5, 6}看成一个数组(从宏观上是有序的了)，对其进行插入排序，直至有序
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
                //j-step 同一组前面的数
                while (j - step >= 0 && arrays[j - step] > temp) {
                    //如果大于，则往前移动
                    arrays[j] = arrays[j -step];
                    j = j-step;
                }
                //前面没有大的数了，就放在这个位置
                arrays[j] = temp;
            }

        }
    }
}

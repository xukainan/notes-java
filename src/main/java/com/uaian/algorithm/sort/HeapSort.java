package com.uaian.algorithm.sort;

/**
 * 堆排序
 * 利用堆完成排序，堆是一种近似完全二叉树的结构，并同时满足堆积的特点：子节点的键值或索引总是小于或大于它的父节点
 * 1. 将无序队列构成最大堆
 * 2. 将堆顶元素与末尾元素交换，将最大元素放到数组的末端
 * 3. 调整结构，使其满足最大堆，然后继续交换堆顶元素与末尾元素，直到有序
 *
 * O(n·logn)
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 13, 4, 3, 8, 5, 10, 13, 4, 2, 6, 7};
        int[] sorted = heapSortMethod(arr, arr.length);
        for (int i = 0; i < sorted.length; i++) {
            System.out.println(sorted[i]);
        }
    }

    private static int[] heapSortMethod(int[] arr, int length) {
        //初始化堆，寻找不满足最大堆定义的元素，然后调整
        //length/2 - 1 找到最后一个有子节点的父节点 比如这里面最后一个父节点是8，它只有一个子节点6
        for (int i = length/2 - 1; i >= 0 ; i--) {
            maxHeap(arr, length, i);
        }
        //将堆顶元素与末尾元素交换，将最大元素放到数组末端
        //length-1 每次最大元素放到最后，就排除堆
//        for (int i = length-1; i >= 1; i--) {
//            arr[i] ^= arr[0];
//            arr[0] ^= arr[i];
//            arr[i] ^= arr[0];
//            //重新调整堆结构
//            maxHeap(arr, i, 0);
//        }
        return arr;
    }

    private static void maxHeap(int[] arr, int length, int index) {
        int leftNode = 2*index + 1;//父节点的左子节点的位置（2i+1）
        int rightNode = 2*index + 2;//父节点的右子节点的位置（2i+2）
        int maxIndex = index;//堆顶

        //maxIndex 找到比现在堆顶元素大的元素赋值
        if(leftNode < length && arr[maxIndex] < arr[leftNode])
            maxIndex = leftNode;
        if(rightNode < length && arr[maxIndex] < arr[rightNode])
            maxIndex = rightNode;

        if(maxIndex != index){
            arr[maxIndex] ^= arr[index];
            arr[index] ^= arr[maxIndex];
            arr[maxIndex] ^= arr[index];
            //继续判断以当前交换后的堆的元素为堆顶的堆是否满足最大堆
            maxHeap(arr, length, maxIndex);
        }


    }
}

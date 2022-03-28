package com.uaian.algorithm.leecode;

import java.util.*;

/**
 * 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> list = spiralOrder(matrix);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }

//    public static List<Integer> spiralOrder(int[][] matrix) {
//        List<Integer> res = new LinkedList<>();
//        if (matrix.length == 0) {
//            return res;
//        }
//        int up = 0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1;
//        while (true) {
//            for (int col = left; col <= right; ++col) {
//                res.add(matrix[up][col]);
//            }
//            if (++up > down) {
//                break;
//            }
//            for (int row = up; row <= down; ++row) {
//                res.add(matrix[row][right]);
//            }
//            if (--right < left) {
//                break;
//            }
//            for (int col = right; col >= left; --col) {
//                res.add(matrix[down][col]);
//            }
//            if (--down < up) {
//                break;
//            }
//            for (int row = down; row >= up; --row) {
//                res.add(matrix[row][left]);
//            }
//            if (++left > right) {
//                break;
//            }
//        }
//        return res;
//    }
}

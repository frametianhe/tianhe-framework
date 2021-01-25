package com.tianhe.writtentest.algorithm.array;

public class RotatingImage {

    public static void main(String[] args) {

        /**
         * 旋转图像
         *
         * 给定一个 n × n 的二维矩阵表示一个图像。
         *
         * 将图像顺时针旋转 90 度。
         *
         * 示例 1:
         *
         * 给定 matrix =
         * [
         *   [1,2,3],
         *   [4,5,6],
         *   [7,8,9]
         * ],
         *
         * 原地旋转输入矩阵，使其变为:
         * [
         *   [7,4,1],
         *   [8,5,2],
         *   [9,6,3]
         * ]
         *
         * 示例 2:
         *
         * 给定 matrix =
         * [
         *   [ 5, 1, 9,11],
         *   [ 2, 4, 8,10],
         *   [13, 3, 6, 7],
         *   [15,14,12,16]
         * ],
         *
         * 原地旋转输入矩阵，使其变为:
         * [
         *   [15,13, 2, 5],
         *   [14, 3, 4, 1],
         *   [12, 6, 8, 9],
         *   [16, 7,10,11]
         * ]
         */
    }

    /**
     * 使用辅助数组
     *
     * 复杂度分析
     *
     * 时间复杂度：O(N^2)，其中 N 是 matrix 的边长。
     *
     * 空间复杂度：O(N^2)，我们需要使用一个和 \textit{matrix}matrix 大小相同的辅助数组。
     *
     * @return
     */
    public static int[][] test01(int[][] matrix){
        int n = matrix.length;
        int[][] matrixNew = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j< n; j++){
                matrixNew[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0;i < n; i++){
            for (int j = 0; j< n; j++){
                matrix[i][j] = matrixNew[i][j];
            }
        }
        return matrix;
    }

}

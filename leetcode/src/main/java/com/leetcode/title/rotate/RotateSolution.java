package com.leetcode.title.rotate;

public class RotateSolution {

    public void rotate(int[][] matrix) {

        int length = matrix.length;

        int[][] result = new int[length][length];

        int flag = length - 1;

        for (int i = 0; i < matrix.length; i++) {
            int x = 0;
            int y = flag;
            for (int j = 0; j < matrix[i].length; j++) {
                int matrix1 = matrix[i][j];

                result[i + flag - y - i][j + flag - x - i] = matrix1;

                x++;
                y--;
            }
        }
        System.arraycopy(result, 0, matrix, 0, length);
    }
}

package com.kritsn.leetCodeJava;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 07, 2025
 */

public class _048Rotate2DArray90 {
    private void rotate(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        if (row == 1 || column == 1) return;
        arrayTranspose(matrix);
        arrayReverse(matrix);
    }

    private void arrayTranspose(int[][] array) {
        int row = array.length;
        int column = array[0].length;
        if (row != column) {
            //array can not be transposed, row and column must be same to transpose the array
            return;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i < j) { //imp or run j loop for (i+1 to till column-1) & in this case we can remove the condition
                    int temp = array[i][j];
                    array[i][j] = array[j][i];
                    array[j][i] = temp;
                }
            }
        }
    }

    private void arrayReverse(int[][] array) {
        int row = array.length;
        int column = array[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column / 2; j++) { //imp j < column/2
                int temp = array[i][j];
                array[i][j] = array[i][column - j - 1];
                array[i][column - j - 1] = temp;
            }
        }
    }

    private void print2DArray(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            // Loop through each column in current row
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            // Move to next line after each row
            System.out.println();
        }
    }

    public static void main(String[] args) {
        _048Rotate2DArray90 rotate2DArray90 = new _048Rotate2DArray90();
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        rotate2DArray90.print2DArray(array);
        System.out.println("-----------");
        rotate2DArray90.rotate(array);
        rotate2DArray90.print2DArray(array);
    }
}

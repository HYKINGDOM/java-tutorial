package com.java.coco.demo;

public class MultidimensionalArray {

    public static void main(String[] args) {
        int[][] inputArray = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};

        MultidimensionalArray multidimensionalArray = new MultidimensionalArray();
        multidimensionalArray.printMultiArray(inputArray);

        //        printMultiArray(inputArray);
        //        codeGame.gameOfLife(inputArray);
        //        printMultiArray(inputArray);
        //        int[][] expectArray = { { 0, 0, 0 }, { 1, 0, 1 }, { 0, 1, 1 }, { 0, 1, 0 } };
        //        assertEquals(expectArray, inputArray);
    }

    public void printMultiArray(int[][] inputArray) {

        int length = inputArray.length;
        System.out.println(length);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < inputArray[j].length; j++) {
                System.out.print(" " + inputArray[i][j]);
            }
            System.out.println();
        }
    }
}

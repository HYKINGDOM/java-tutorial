package com.leetcode.hw.frogsteps;

import java.util.HashSet;
import java.util.Scanner;

public class FrogSteps {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //x+2y=n
        while (in.hasNext()) {
            String next = in.nextLine();

            int length = Integer.parseInt(next);

            int first = 1;
            int second = 1;
            int temp = 1;
            for (int i = 2; i <= length; i++) {
                temp = first + second;
                first = second;
                second = temp;
            }

//            for (int i = 0; i <= twoSteps; i++) {
//                for (int j = 0; j <= length; j++) {
//                    if ((2 * i + j) == length) {
//                        num++;
//                    }
//                }
//            }

            System.out.println(temp);
        }
    }


    private int sumY(int x, int y, int length, int flag) {

        if ((x + (2 * y)) == length) {
            return flag;
        }
        y++;
        flag++;

        return sumY(x, y, length, flag);
    }
}

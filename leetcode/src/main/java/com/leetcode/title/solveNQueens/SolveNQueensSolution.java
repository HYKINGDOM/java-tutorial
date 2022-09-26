package com.leetcode.title.solveNQueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SolveNQueensSolution {

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> lists = new ArrayList<>();

        char[][] chars = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(chars[i], '.');
        }

        Stack<String> path = new Stack<>();

        boolean[][] flag = new boolean[n][n];

        addList(lists, chars, path, flag, 0, n);

        return lists;
    }

    public void addList(List<List<String>> lists, char[][] strings, Stack<String> path, boolean[][] flag, int length, int n) {

        if (length == n) {
            lists.add(path);
            return;
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!flag[i][j]) {
                    flag[i][j] = true;
                    strings[i][j] = 'Q';
                    path.push(String.valueOf(strings[i][j]));
                    addList(lists, strings, path, flag, length + 1, n);
                    flag[i][j] = false;
                    path.pop();
                }
            }
        }
    }

    public boolean judgeRow(char[][] chars, int r, int n) {
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (chars[r][i] == 'Q') {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean judgeColumn(char[][] chars, int c, int n) {
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (chars[i][c] == 'Q') {
                flag = false;
                break;
            }
        }
        return flag;
    }


    public boolean judgeDiagonalRightLine(char[][] chars, int c, int r, int n) {
        boolean flag = false;
        int temp = 0;
        //右斜线
        if (c > r) {
            temp = c - r;
            c = temp;
            r = 0;
        } else if (c == r) {
            c = 0;
            r = 0;
        } else {
            temp = r - c;
            r = temp;
            c = 0;
        }
        for (int i = 0; i < n; i++) {
            int i1 = r + 1;
            int j1 = c + 1;
            if (i1 < n || j1 < n) {
                if (chars[i1][j1] == 'Q') {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * @param chars 棋盘
     * @param c     列
     * @param r     行
     * @param n     总长
     * @return 左斜线是否有Q
     */
    public boolean judgeDiagonalLeftLine(char[][] chars, int c, int r, int n) {
        boolean flag = false;
        int temp = 0;
        //右斜线
        if (c == 0 || r == n - 1) {
            temp = c;
            c = r;
            r = temp;
        } else {
            while (c < n - 1 && r > 0) {
                r = r - 1;
                c = c + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (r < n || c >= 0) {
                if (chars[r][c] == 'Q') {
                    flag = true;
                    break;
                }
            }
            r = r + 1;
            c = c - 1;
        }
        return flag;
    }


}

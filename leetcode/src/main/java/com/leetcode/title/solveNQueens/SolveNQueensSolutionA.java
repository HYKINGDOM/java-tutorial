package com.leetcode.title.solveNQueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SolveNQueensSolutionA {

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        //建立一个棋盘
        char[][] chessboard = new char[n][n];
        //棋盘初始化都是‘.’
        for (int i = 0; i < n; i++) {
            Arrays.fill(chessboard[i], '.');
        }
        backtracking(n, 0, chessboard);
        return res;
    }

    //n是应该搜索到的最大层数，level是现在所在层数
    public void backtracking(int n, int level, char[][] chessboard) {
        //基线条件
        if (level == n) {
            res.add(Array2List(chessboard));
            return;
        }

        //横向遍历
        for (int i = 0; i < n; i++) {
            if (judge(chessboard, level, i)) {
                chessboard[level][i] = 'Q';
                backtracking(n, level + 1, chessboard);
                chessboard[level][i] = '.';
            }
        }
        return;
    }


    public boolean judge(char[][] chessboard, int level, int i) {
        int n = chessboard.length;
        //判断同一列有没有皇后
        for (int x = 0; x < n; x++) {
            if (chessboard[x][i] == 'Q') {
                return false;
            }
        }

        //判断对角线有没有皇后
        int temp_x = level + 1;
        int temp_y = i + 1;
        while (temp_x < n && temp_y < n) {
            if (chessboard[temp_x][temp_y] == 'Q') {
                return false;
            }
            temp_x++;
            temp_y++;
        }
        temp_x = level - 1;
        temp_y = i - 1;
        while (temp_x >= 0 && temp_y >= 0) {
            if (chessboard[temp_x][temp_y] == 'Q') {
                return false;
            }
            temp_x--;
            temp_y--;
        }

        //判断反对角线有没有皇后
        temp_x = level - 1;
        temp_y = i + 1;
        while (temp_x >= 0 && temp_y < n) {
            if (chessboard[temp_x][temp_y] == 'Q') {
                return false;
            }
            temp_x--;
            temp_y++;
        }

        temp_x = level + 1;
        temp_y = i - 1;
        while (temp_x < n && temp_y >= 0) {
            if (chessboard[temp_x][temp_y] == 'Q') {
                return false;
            }
            temp_x++;
            temp_y--;
        }
        return true;
    }


    //将矩阵chessboard里的‘.’和‘Q’都存到最终结果的res集合中。
    public List Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();
        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }

}

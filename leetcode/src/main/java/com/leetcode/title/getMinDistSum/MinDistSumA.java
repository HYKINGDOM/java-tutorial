package com.leetcode.title.getMinDistSum;

public class MinDistSumA {

    double ans;//答案存放处
    int[][] di = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};//上、右、下、左方向数组

    public double getMinDistSum(int[][] positions) {
        double[] centre = new double[2];//服务中心点
        /*先假设各个点的平均位置处是服务中心点*/
        for (int i = 0; i < positions.length; i++) {
            centre[0] += positions[i][0];
            centre[1] += positions[i][1];
        }
        centre[0] /= positions.length;
        centre[1] /= positions.length;
        ans = getSumDistances(centre, positions);//得到平均点到各个点的距离和
        /*以1为最大步长，向centre上、右、下、左查找更优解直到步长减小到0.00001*/
        changeAns(centre, positions, 1, 1e-5);
        return ans;
    }

    private void changeAns(double[] centre, int[][] positions, double test_distances, double end) {//求更优解
        if (test_distances <= end)
            return;
        int flag = 0;
        double[] newpoint = new double[2];
        for (int k = 0; k < di.length; k++) {
            newpoint[0] = centre[0] + test_distances * di[k][0];
            newpoint[1] = centre[1] + test_distances * di[k][1];
            double test = getSumDistances(newpoint, positions);
            if (test < ans) {
                centre[0] = newpoint[0];
                centre[1] = newpoint[1];
                ans = test;
                flag = 1;
                break;
            }
        }
        changeAns(centre, positions, flag == 0 ? test_distances / 2 : test_distances, end);
    }

    public double getSumDistances(double[] centre, int[][] positions) {//求centre点到各个点的距离和
        double res = 0;
        for (int i = 0; i < positions.length; i++) {
            double x = positions[i][0] - centre[0];
            double y = positions[i][1] - centre[1];
            res += Math.sqrt(x * x + y * y);
        }
        return res;
    }

}

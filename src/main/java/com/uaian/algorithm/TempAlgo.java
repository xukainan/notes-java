package com.uaian.algorithm;


public class TempAlgo {
    public static void main(String[] args) {
        int n = 30;
        int res = ClimbStairsMethod(n);
        System.out.println("爬" + n + "层楼梯，有多少种可能：" + res);
    }

    private static int ClimbStairsMethod(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n-1];

    }


}

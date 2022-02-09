package com.uaian.algorithm.dp;

/**
 * There are n stairs, a person standing at the bottom wants to reach the top. The person can climb either 1 stair or 2 stairs at a time. Count the number of ways, the person can reach the top.
 * Examples:
 * Input: n = 4
 * Output: 5
 * (1, 1, 1, 1), (1, 1, 2), (2, 1, 1), (1, 2, 1), (2, 2)
 */
public class ClimbStairs {
    public static void main(String[] args) {
        int n = 30;
        int res = ClimbStairsMethod(n);
        System.out.println("爬" + n + "层楼梯，有多少种可能：" + res);
    }

    private static int ClimbStairsMethod(int n) {
        if(n <= 1){
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}

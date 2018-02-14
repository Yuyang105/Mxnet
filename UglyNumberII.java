/**
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number, and n does not exceed 1690.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
*/

class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        int index2 = 0, index3 = 0, index5 = 0;
        
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[index2] * 2, Math.min(dp[index3] * 3, dp[index5] * 5));
            if (dp[i] == dp[index2] * 2) index2++;
            if (dp[i] == dp[index3] * 3) index3++;
            if (dp[i] == dp[index5] * 5) index5++;
        }
        
        return dp[n - 1];
    }
}

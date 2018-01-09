/**
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.
*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null) return 0;
        
        /* basic case */
        int len = prices.length;
        if (k > len / 2) {
            int res = 0;
            for (int i = 1; i < len; i++)
                if (prices[i] > prices[i - 1])
                    res+= prices[i] - prices[i - 1];
            return res;
        }
        
        /* dp case */
        int[][] dp = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int temp = -prices[0];
            for (int j = 1; j < len; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], temp + prices[j]);
                temp = Math.max(temp, dp[i - 1][j - 1] - prices[j]);
            }
        }
        return dp[k][len - 1];
    }
}

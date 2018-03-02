/**

这个题目trick的地方在于，我们抓buy1, buy2, sell1, sell2的时候，我们是倒过来处理的，可以这么理解：

basically we want to maximize the formula: -price1 + price2 - price3 + price4, 
where every price appear in that order. So every time we visit a new price, 
we update the max possible value of 4 (partial) formula. That’s why we use max for each of the 4 states.

*/


/**
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).


*/

class Solution {
    public int maxProfit(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE, release1 = 0, release2 = 0;
        for (int price : prices) {
            release2 = Math.max(release2, hold2 + price);
            hold2 = Math.max(hold2, release1 - price);
            release1 = Math.max(release1, hold1 + price);
            hold1 = Math.max(hold1, -price);
        }
        return release2;
    }
}

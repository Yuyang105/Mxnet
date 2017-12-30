/**
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
*/
class Solution {
    public int maxCoins(int[] nums) {
        // initial
        int[] a = new int[nums.length + 2];
        int n = 1;
        for (int i : nums)
            a[n++] = i;
        a[0] = a[n++] = 1;
        
        // function
        int[][] f = new int[n][n];
        for (int dis = 2; dis < n; dis++) {
            for (int left = 0; left + dis < n; left++) {
                int right = left + dis;
                for (int k = left + 1; k <= right - 1; k++) {
                    f[left][right] = Math.max(f[left][right], 
                                              f[left][k]+ f[k][right] + 
                                              a[left]* a[k] * a[right]);
                }
            }
        }
        return f[0][n - 1];
    }
}

/**
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Credits:
Special thanks to @ifanchu for adding this problem and creating all test cases. Also thanks to @ts for adding additional test cases.
*/
class Solution {
    /**
     * Naive DP solution *
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        
        int[] f = new int[nums.length + 1];
        f[0] = 0;
        f[1] = nums[0];
        
        for (int i = 2; i <= n; i++)
            f[i] = Math.max(f[i - 2] + nums[i - 1], f[i - 1]);
        
        return f[n];
    }
    */
    
    /** 
     * Improved rolling version *
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        
        int[] f = new int[2];
        f[0] = 0;
        f[1] = nums[0];
        
        for (int i = 2; i <= n; i++)
            f[i % 2] = Math.max(f[(i - 2) % 2] + nums[i - 1], f[(i - 1) % 2]);
        
        return f[n % 2];
    }
}

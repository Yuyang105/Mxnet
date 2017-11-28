/**
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Note:
You can assume that you can always reach the last index.

*/

class Solution {
    /** DP version: time limit exceeded
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int[] f = new int[nums.length];
        f[0] = 1;
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] != 0 && (j + nums[j]) >= i) {
                    if (f[i] != 0 && f[i] > f[j] + 1)
                        f[i] = f[j] + 1;
                    if (f[i] == 0)
                        f[i] = f[j] + 1;
                }
            }
        }
        return f[nums.length - 1] - 1;
    }
    */
    
    /** Greedy version */
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        
        int start = 0, end = 0, jumps = 0;
        while (end < nums.length - 1) {
            jumps++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (nums[i] + i > farthest) {
                    farthest = nums[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
        }
        return jumps;
    }
}

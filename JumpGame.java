/**
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

*/

class Solution {
    /** DP version
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return true;
        
        boolean[] f = new boolean[nums.length];
        f[0] = true;
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && j + nums[j] >= i) {
                    f[i] = true;
                    break;
                }
            }
        }
        
        return f[nums.length - 1];
    }
    */
    
    public boolean canJump(int[] nums) {
        if ( nums == null || nums.length == 0) return false;
        
        int farthest = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i <= farthest && nums[i] + i >= farthest) {
                farthest = nums[i] + i;
            }
        }
        
        return farthest >= nums.length - 1;
    }
}

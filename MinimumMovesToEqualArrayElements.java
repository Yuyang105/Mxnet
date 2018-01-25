/**
Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.

Example:

Input:
[1,2,3]

Output:
3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
*/

class Solution {
    /** 
     *  Approach 1.
    public int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int min = nums[0], res = 0;
        for (int num : nums)
            min = Math.min(num, min);
        for (int num : nums)
            res += num - min;
        return res;
    }
    */
    
    /** 
     *  Approach 2: 
     *      len * (min + k) = sum + k * (len - 1) * 1
     *      k = sum - min * len
     */
    public int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int min = nums[0], sum = 0;
        for (int num : nums) {
            min = Math.min(num, min);
            sum += num;
        }
        return sum - min * nums.length;
    }
}

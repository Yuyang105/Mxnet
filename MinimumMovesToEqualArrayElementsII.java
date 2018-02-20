/**

题目大意：
给定非空整数数组，求使得数组中的所有元素均相等的最小移动次数，一次移动是指将某个元素加1或者减1。

排序之后，从两边往中间走，最大和最小之间的差距，是一定要填补上的，不管+1 还是 -1，所以最后都等于中位数。

*/

/**
Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
*/

class Solution {
    public int minMoves2(int[] nums) {
        int res = 0;
        Arrays.sort(nums);
        int mid = nums[nums.length / 2];
        for (int num : nums)
            res += Math.abs(num - mid);
        return res;
    }
}

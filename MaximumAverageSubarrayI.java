/**

给一个有n个元素的数组，找其中平均值最大的长度为k的子串，要求返回最大的平均值。
先算出前k个元素的sum，之后依次向右枚举，每次删掉最左边的元素，加上最右边的元素，相当于窗口移动，
并在每次移动时，更新maxSum。当枚举结束后，返回 maxSum / k。

*/


/**
Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
Note:
1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].
*/
public class Solution {
    public double findMaxAverage(int[] nums, int k) {
        long sum = 0;
        for (int i = 0; i < k; i++) sum += nums[i];
        long max = sum;
        
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }
        
        return max / 1.0 / k;
    }
}

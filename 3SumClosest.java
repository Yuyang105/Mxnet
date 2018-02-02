/** 
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/

// 也就是3SUM
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        int res = nums[0] + nums[1] + nums[nums.length - 1];    // 用MAX_VALUE，遇到负数会溢出
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > target)
                    right--;
                else
                    left++;
                if (Math.abs(res - target) > Math.abs(sum - target)) {
                    res = sum;
                }
            }
        }
        return res; 
    }
}

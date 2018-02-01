/**
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

public class Solution {
    /** Time: O(n^2); Space: O(n). */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);      // 一定要排序
        for (int i = 0; i + 2 < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i -1]) continue;   // skip same result 跳掉重复的

            // Two sum
            int j = i + 1, k = nums.length -1;
            int target = - nums[i];
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--; 
                    while (j < k && nums[j] == nums[j - 1]) j++; // skip same 去重
                    while (j < k && nums[k] == nums[k + 1]) k--; // skip same 去重
                }
                else if (nums[j] + nums[k] > target)
                    k--;
                else
                    j++;
            }
        }
        return res;
    }
}

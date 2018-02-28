/**
等于冒泡交换，one-pass，直接看是奇数位，就应该比前一个数大，否则应该小于前一个数。不满足就交换。

时间复杂度: O(n)
空间复杂度: O(1)

*/

/**
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
*/

class Solution {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 1 && nums[i - 1] > nums[i]) || (i % 2 == 0 && nums[i - 1] < nums[i])) {
                int temp = nums[i - 1];
                nums[i - 1] = nums[i];
                nums[i] = temp;
            }
        }
    }
}

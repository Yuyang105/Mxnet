/**
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.

*/

class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        
        int start = 0, end = nums.length - 1;
        int target = nums[nums.length - 1];
        
        // find the first element <= target
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        if (nums[start] <= target) {
            return nums[start];
        }
        else return nums[end];
    }
}

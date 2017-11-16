/**
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
*/


/** 
The worst time complexity in this problem is O(n). So, binary search is not a necessary anymore.
*/

class Solution {
    public int findMin(int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] < res) res = nums[i];
        return res;
    }
}

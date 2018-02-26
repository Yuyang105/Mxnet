/**
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/
/**

翻折一下，所以二分法，至少有半段是一定有序的。所以：
1.  if      前半段有序：
        target落在前半段，end往前面移，否则就在后半段，start往后移。
        
2.  else    后半段有序：
        target落在后半段，start往后移，否则end往前移。

*/
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            if (nums[start] < nums[mid]) {          // 确立前半段有序
                if (nums[start] <= target && target < nums[mid])
                    end = mid;
                else
                    start = mid;
            } else {                                // 那至少后半段有序
                if (nums[mid] < target && target <= nums[end]) 
                    start = mid;
                else
                    end = mid;
            }
        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }
}

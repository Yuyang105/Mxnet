/**

In each round, we divide our array into two parts and sort them. So after "int cnt = mergeSort(nums, s, mid) + mergeSort(nums, mid+1, e); ", the left part and the right part are sorted and now our only job is to count how many pairs of number (leftPart[i], rightPart[j]) satisfies leftPart[i] <= 2*rightPart[j].
For example,
left: 4 6 8 right: 1 2 3
so we use two pointers to travel left and right parts. For each leftPart[i], if j<=e && nums[i]/2.0 > nums[j], we just continue to move j to the end, to increase rightPart[j], until it is valid. Like in our example, left’s 4 can match 1 and 2; left’s 6 can match 1, 2, 3, and left’s 8 can match 1, 2, 3. So in this particular round, there are 8 pairs found, so we increases our total by 8.

Similar to this https://leetcode.com/problems/count-of-smaller-numbers-after-self/. But the main difference is: here, the number to add and the number to search are different (add nums[i], but search nums[i]/2.0), so not a good idea to combine build and search together.

*/

/**
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.
*/
class Solution {
    private int res;
    
    public int reversePairs(int[] nums) {
        res = 0;
        mergeSort(nums, 0, nums.length - 1);
        return res;
    }
    
    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right)
            return;
        
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        
        // count elements
        int count = 0;
        for (int l = left, r = mid + 1; l <= mid; ) {
            if (r > right || (long)nums[l] <= 2 * (long)nums[r]) {
                l++;
                res += count;
            } else {
                r++;
                count++;
            }
        }
        
        // merge sort
        int[] temp = new int[right - left + 1];
        for (int l = left, r = mid + 1, k = 0; l <= mid || r <= right; ) {
            if (l <= mid && (r > right || nums[l] < nums[r])) {
                temp[k++] = nums[l++];
            } else {
                temp[k++] = nums[r++];
            }
        }
        for (int i = 0; i < temp.length; i++) {
            nums[left + i] = temp[i];
        }
        
    }
}

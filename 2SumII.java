/**
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution and you may not use the same element twice.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

class Solution {
    /** Two pointer */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) return null;
        int low = 0, high = numbers.length - 1, sum = 0;
        while (low < high) {
            sum = numbers[low] + numbers[high];
            if (sum > target)
                high--;
            else if (sum < target)
                low++;
            else
                return new int[]{low + 1, high + 1};
        }
        return null;
    }
    
    /** 
      * Brute force: O(n^2)
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) return null;
        
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target)
                    return new int[]{i + 1, j + 1};
            }
        }
        return null;
    }
    */
}

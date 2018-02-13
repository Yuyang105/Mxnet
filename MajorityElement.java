/**
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/

class Solution {
	// sort
	// time: o(nlogn)
	// space: o(1)
    public int majorityElement1(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length / 2];
    }
    
    // hashmap
    // time: o(n)
    // space: o(n)
    public int majorityElement2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i))
                map.put(i, map.get(i) + 1);
            else
                map.put(i, 1);
            if (map.get(i) > nums.length / 2)
                return i;
        }
        return -1;
    }
    
    // boyer-moore algorithm
    // time: o(n)
    // space: o(1) 
    public int majorityElement(int[] nums) {
        int candidate = 0, count = 0;
        for (int i : nums) {
            if (count == 0) {
                candidate = i;
                count++;
            }
            else if (i == candidate)
                count++;
            else
                count--;
        }
        return candidate;
    }



}

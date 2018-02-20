/**

1.  直接排序，然后返回中间值。有点cheating的意思。
    时间复杂度：O(nlogn), 空间复杂度：O(1)。

2.  HashMap: 遍历一遍数组，给每个元素计数。
             key是元素，value是出现次数，一旦出现次数超过一半，返回。
    时间复杂度：O(n), 空间复杂度：O(n)。

3.  boyer-moore：起一个candidate，起一个count，遍历一次数组。
                 if count = 0，candidate等于当前元素。
                 if 当前元素 = candidate，count++；否则 count--；
                 最终返回candidate。
    时间复杂度：O(n), 空间复杂度：O(1)。

*/

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

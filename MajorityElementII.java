/**
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
The algorithm should run in linear time and in O(1) space.
*/

class Solution {
	public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
		if (nums == null || nums.length == 0) return res;
		
        // use boyer-moore algorithm select 2 candidates
        int candidate1 = 0, candidate2 = 0, count1 = 0, count2 = 0;
		for (int i : nums) {
			if (i == candidate1)
                count1++;
            else if (i == candidate2)
                count2++;
            else if (count1 == 0) {
                candidate1 = i;
                count1 = 1;
            }
            else if (count2 == 0) {
                candidate2 = i;
                count2 = 1;
            }
            else {
                count1--;
                count2--;
            }
        }
        
        // check the 2 candidates
        count1 = 0;
        count2 = 0;
        for (int i : nums) {
            if (i == candidate1) 
                count1++;
            else if (i == candidate2)
                count2++;
        }
        if (count1 > nums.length / 3)
            res.add(candidate1);
        if (count2 > nums.length / 3)
            res.add(candidate2);
        return res;
    }
}

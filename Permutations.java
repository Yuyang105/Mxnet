/**
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/

class Solution {
    // time: O(n! * n)      space: O(n)
	public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permuteHelper(res, new ArrayList<>(), nums);
        return res;
    }
    
    private void permuteHelper(List<List<Integer>> res, List<Integer> list, int[] nums) {
    	if (list.size() == nums.length) {
        	res.add(new ArrayList<Integer>(list));
    	}
    	else {
	        for (int i = 0; i < nums.length; i++) {
	        	if (list.contains(nums[i])) continue;   // O(n)
	            list.add(nums[i]);
	            permuteHelper(res, list, nums);
	            list.remove(list.size() - 1);
	        }
    	}
    }
    
    // time: O(n!)      space: O(n)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, 0, nums);
        return res;
    }
    
    private static void helper(List<List<Integer>> res, int start, int[] nums) {
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(new ArrayList<>(list));
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            helper(res, start + 1, nums);
            swap(nums, start, i);
        }
        
    }
     
    private static void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
  
}

class Solution {
	public List<List<Integer>> permute(int[] nums) {
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
	        	if (list.contains(nums[i]))
	        		continue;
	            list.add(nums[i]);
	            permuteHelper(res, list, nums);
	            list.remove(list.size() - 1);
	        }
    	}
    }
}

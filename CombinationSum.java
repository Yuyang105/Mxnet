class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtracing(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }
    
    private void backtracing(List<List<Integer>> res, List<Integer> tempList, int[] candidates, int target, int pos) {
        for (int i = pos; i < candidates.length; i++) {
            if (candidates[i] > target)
                continue;
            tempList.add(candidates[i]);
            if (candidates[i] == target)
                res.add(new ArrayList<Integer>(tempList));
            backtracing(res, tempList, candidates, target - candidates[i], i);
            tempList.remove(tempList.size() - 1);
        }
    }
}

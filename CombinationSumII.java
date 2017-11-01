class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtracing(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }
    private void backtracing(List<List<Integer>> res, List<Integer> tempList, int[] candidates, int target, int pos) {
        if (target == 0) res.add(new ArrayList<Integer>(tempList)); 
        else if (target < 0) return;
        else {
            for (int i = pos; i < candidates.length; i++) {
                if (i > pos && candidates[i] == candidates[i - 1]) continue;
                tempList.add(candidates[i]);
                backtracing(res, tempList, candidates, target - candidates[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}

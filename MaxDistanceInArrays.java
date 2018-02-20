/**

list中的每一个list是有序的，所以list.get(0)是最小的，list.get(list.size() - 1)是最大的。 
用当前数组的最大值减去min，用最小值减去max。结合当前list更新最大值和最小值。

Time complexity : O(n)O(n). We traverse over the listlist of length nn once only.
Space complexity : O(1)O(1). Constant extra space is used.

*/

/**
Given m arrays, and each array is sorted in ascending order. Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:
Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
Note:
Each given array will have at least 1 number. There will be at least two non-empty arrays.
The total number of the integers in all the m arrays will be in the range of [2, 10000].
The integers in the m arrays will be in the range of [-10000, 10000].
*/

class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        if (arrays == null) return 0;
        
        Integer res = 0, min = arrays.get(0).get(0), max = arrays.get(0).get(arrays.get(0).size() - 1);
        for (int i = 1; i < arrays.size(); i++) {
            res = Math.max(res, Math.max(Math.abs(arrays.get(i).get(arrays.get(i).size() - 1) - min),
                                        Math.abs(max - arrays.get(i).get(0))));
            min = Math.min(min, arrays.get(i).get(0));
            max = Math.max(max, arrays.get(i).get(arrays.get(i).size() - 1));
        }
        return res;
    }
    
    /**
     * Brute Force: O(n^2) 
    public int maxDistance(List<List<Integer>> arrays) {
        int res = 0;
        if (arrays == null) return res;
        
        for (int i = 0; i < arrays.size() - 1; i++) {
            for (int j = i + 1; j < arrays.size(); j++) {
                res = Math.max(res, 
                               Math.abs(arrays.get(i).get(arrays.get(i).size() - 1) 
                                        - arrays.get(j).get(0)));
                res = Math.max(res, 
                               Math.abs(arrays.get(j).get(arrays.get(j).size() - 1) 
                                        - arrays.get(i).get(0)));
            }
        }
        return res;
    }
    */
}

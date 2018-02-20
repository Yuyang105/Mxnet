/**

通过一直记录一个最小值，遍历数组，一旦发现和alower有距离，就是一个空隙。

时间复杂度O(n), 空间复杂度O(1).

*/

/**

Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].

*/
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        long alower = (long)lower;
        for (int num : nums) {
            if (num == alower) {
                alower++;
            } else if (alower < num) {
                if (alower + 1 == num) {
                    res.add((int)alower + "");
                } else {
                    res.add(alower + "->" + (num - 1));
                }
                alower = (long) num + 1;
            }
        }
        if (alower == (long)upper) res.add((int)alower + "");
        else if (alower < (long)upper) res.add(alower + "->" + upper);
        return res;
    }
}

/**
Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

Credits:
Special thanks to @memoryless for adding this problem and creating all test cases.
*/

/**

Trick: 通过最大最小点相加，确定对称轴。
在找最大最小点的同时，我们可以把所有的点加入到hashset里，再遍历一遍找对称的点。

*/
class Solution {
    public boolean isReflected(int[][] points) {
        HashSet<String> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] pair : points) {
            set.add(pair[0] + "," + pair[1]);
            min = Math.min(min, pair[0]);
            max = Math.max(max, pair[0]);
        }
        
        int sum = min + max;
        for (int[] pair : points) {
            if (!set.contains(sum - pair[0] + "," + pair[1]))
                return false;
        }
        
        return true;
    }
}

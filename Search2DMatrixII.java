/**

[               ←   15
  [1,   4,  7, 11,  ↓],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

可以看做一棵树，往左边走就是变小， 往右边走就是变大。那么从top-right开始走起，就能搞定。

时间复杂度：O(m + n)，空间复杂度：O(1)。

*/

/**
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return false;
        
        int r = 0, c = matrix[0].length - 1;
        while (matrix[r][c] != target) {
            if (matrix[r][c] > target)
                c--;
            else
                r++;
            if (c < 0 || r >= matrix.length)
                return false;
        }
        
        return true;
    }
}

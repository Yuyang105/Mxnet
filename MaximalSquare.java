/**
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.
*/
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        if (matrix[0] == null || matrix[0].length == 0) return 0;
        
        int res = 0;
        
        // state
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] f = new int[n][m];
        
        // initialize
        for (int i = 0; i < n; i++) {
            f[i][0] = matrix[i][0] - '0';
            res = Math.max(res, f[i][0]);
        }
        for (int i = 0; i < m; i++) {
            f[0][i] = matrix[0][i] - '0';
            res = Math.max(res, f[0][i]);
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1')
                    f[i][j] = Math.min(f[i - 1][j], Math.min(f[i][j - 1], f[i - 1][j - 1])) + 1;
                else
                    f[i][j] = 0;
                res = Math.max(res, f[i][j]);
            }
        }
        
        return res * res;
    }
}

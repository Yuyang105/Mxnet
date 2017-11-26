/**
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
[[1,3,1],
 [1,5,1],
 [4,2,1]]
Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
*/

class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        if (grid[0] == null || grid[0].length == 0) return 0;
        
        // state
        int n = grid.length;
        int m = grid[0].length;
        int[][] f = new int[n][m];
        
        // initialize
        f[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            f[i][0] = f[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            f[0][i] = f[0][i - 1] + grid[0][i];
        }
        
        // top down
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];
            }
        }
        
        return f[n - 1][m - 1];
        
        
        
    }
}

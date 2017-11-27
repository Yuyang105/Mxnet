/**
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
*/
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) return 0;
        if (obstacleGrid[0] == null || obstacleGrid[0].length == 0) return 0;
        
        // state
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) return 0;
        int[][] f = new int[m][n];
        
        // initialize
        f[0][0] = 1;
        boolean obstacle = false;
        for (int i = 1; i < n; i ++) {
            if (obstacleGrid[0][i] == 1 || obstacle) {
                f[0][i] = 0;
                obstacle = true;
            }
            else 
                f[0][i] = 1;
        }
        
        obstacle = false;
        for (int i = 1; i < m; i++) {
            if(obstacleGrid[i][0] == 1 || obstacle) {
                f[i][0] = 0;
                obstacle = true;
            }
            else
                f[i][0] = 1;
        }
        
        // top down
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j ++) {
                if (obstacleGrid[i][j] == 1)
                    f[i][j] = 0;
                else
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        
        return f[m -1][n - 1];
    }
}

/**
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.
*/

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        boolean visited[][] = new boolean[grid.length][grid[0].length];
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1')
                    if (helper(i, j, grid, visited) > 0)
                        res++;
            }
        }
        return res;
    }
    
    private int helper(int r, int c, char[][] grid, boolean[][] visited) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || visited[r][c] || grid[r][c] == '0')
            return 0;
        visited[r][c] = true;
        return (1 + helper(r, c + 1, grid, visited) + helper(r, c - 1, grid, visited) + helper(r + 1, c, grid, visited) + helper(r - 1, c, grid, visited));
    }
}

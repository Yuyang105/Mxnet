class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result += 4;
                    if (i > 0 && grid[i-1][j] == 1) result -= 2;
                    if (j > 0 && grid[i][j-1] == 1) result -= 2;
                }
            }
        }
        return result;
        /*
        int res = 0;
        int[][] cheat = new int[grid.length+2][grid[0].length+2];
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                cheat[i+1][j+1] = grid[i][j];
        for (int i = 0; i <grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (cheat[i+2][j+1] == 0) res++;
                    if (cheat[i][j+1] == 0) res++;
                    if (cheat[i+1][j+2] == 0) res++;
                    if (cheat[i+1][j] ==0) res++;
                }
            }
        }
        return res;   
        */
    }
}

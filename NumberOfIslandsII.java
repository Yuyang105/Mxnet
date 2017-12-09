/**
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

Challenge:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
*/

class Solution {
     /** convert 2D position to 1D id */
    public int convertID(int x, int y, int m) {
        return x * m + y;
    }
    
    /** Union Find */
    class UnionFind {
        HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();
            
        UnionFind(int n, int m) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int id = convertID(i, j, m);
                    father.put(id, id);
                }
            }
        }
            
        int find(int x) {
            int parent = father.get(x);
            while (parent != father.get(parent)) {
                parent = father.get(parent);
            }
            int temp = -1;
            int fa = x;
            while (fa != father.get(fa)) {
                temp = father.get(fa);
                father.put(fa, parent);
                fa = temp;
            }
            return parent;
        }
            
        void union(int x, int y) {
            int fa_x = find(x);
            int fa_y = find(y);
            if (fa_x != fa_y) {
                father.put(fa_x, fa_y);
            }
        }
    }
        
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<Integer>();
        if (positions == null) return res;
        
        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};
        int[][] island = new int[m][n];
        
        UnionFind unionFind = new UnionFind(n, m);
        int count = 0;
        
        for (int[] position : positions) {
            int x = position[0];
            int y = position[1];
            if (island[x][y] != 1) {
                count++;
                island[x][y] = 1;
                int id = convertID(x, y, n);
                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if (0 <= nx && nx < m && 0 <= ny && ny < n && island[nx][ny ] == 1) {
                        int nid = convertID(nx, ny, n);
                        int fa = unionFind.find(id);
                        int nfa = unionFind.find(nid);
                        if (fa != nfa) {
                            count--;
                            unionFind.union(id, nid);
                        }
                    }
                }
            }
        res.add(count);
        }
        return res;
    }
}

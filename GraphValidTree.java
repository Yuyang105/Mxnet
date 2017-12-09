/**
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/
class Solution {
    /** Union Find */
    class UnionFind {
        HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();
            
        UnionFind(int n) {
            for (int i = 0; i < n; i++) {
                father.put(i, i);
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
    
    /** Solution */
    public boolean validTree(int n, int[][] edges) {
        if (edges == null) return false;
        
        UnionFind uf = new UnionFind(n);
        
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int x_fa = uf.find(x);
            int y_fa = uf.find(y);
            if (x_fa == y_fa) 
                return false;
            else 
                uf.union(x, y);
        }
        
        int fa = uf.find(0);
        for (int i = 1; i < n; i ++) {
            if (fa != uf.find(i))
                return false;
        }
        return true;
    }
}

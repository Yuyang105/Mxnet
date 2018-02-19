/**

两种做法：

· More intuitive: Graph + DFS ==> 通过构建节点类，插到一个map里，形成我们的图
                                  有了图以后，我们就可以用DFS查找我们的结果了
                                   
· More efficient: Union Find  ==> 先把所有的变量map成id， 也需要一个节点类，
                                  节点中需要存两个变量：爸爸id、到爸爸id的value。
                                  然后将所有的equations都存union进来。
                                  进行query。在union的同时，我们已经归好类了，
                                  query时，一旦父节点不相同，直接代表到不了。这就是效率高的原因。

*/


/**
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

*/
class Solution {
    /**
     *  Graph + DFS
     *  
     *  Time: O(V + E); Space: O(n) - 栈空间
     *              ↑ E = equations.length, V = 2E (最多)
     *
     */
    public double[] calcEquation1(String[][] equations, double[] values, String[][] queries) {
        // 先构建我们的图
        HashMap<String, List<GraphNode>> map = new HashMap<String, List<GraphNode>>();
        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            if (!map.containsKey(equation[0])) {
                map.put(equation[0], new ArrayList<GraphNode>());
            }
            map.get(equation[0]).add(new GraphNode(equation[1], values[i]));
            if (!map.containsKey(equation[1])) {
                map.put(equation[1], new ArrayList<GraphNode>());
            }
            map.get(equation[1]).add(new GraphNode(equation[0], 1 / values[i]));
        }
        
        // 处理我们的queries
        double[] res = new double[queries.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = query(queries[i][0], queries[i][1], 1, new HashSet<>(), map);
        }
        return res;
    }
    
    // DFS 搜索我们的图
    private double query(String start, String end, double value, HashSet<String> visited, HashMap<String,List<GraphNode>> map) {
        if (visited.contains(start)) return -1;
        if (!map.containsKey(start)) return -1;
        if (start.equals(end)) return value;
        
        visited.add(start);
        for (GraphNode next : map.get(start)) {
            double toNext = query(next.variable, end, value * next.value, visited, map);
            if (toNext != - 1.0) return toNext;
        }
        visited.remove(start);
        return -1;
    }
    
    private class GraphNode {
        String variable;
        double value;
        GraphNode(String variable, double value) {
            this.variable = variable;
            this.value = value;
        }
    }
    
    /**
     *  Union Find
     *
     */
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // Map String to Integer
        Map<String, Integer> idMap = new HashMap<String, Integer>();
        int len = 0;
        for (String[] words : equations) {
            if (!idMap.containsKey(words[0]))
                idMap.put(words[0], len++);
            if (!idMap.containsKey(words[1]))
                idMap.put(words[1], len++);
        }
        
        // Initialize parent index and value
        Node[] nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(i);
        }
        
        // Union
        for (int i = 0; i < equations.length; i++) {
            String[] keys = equations[i];
            int id1 = idMap.get(keys[0]);
            int id2 = idMap.get(keys[1]);
            int parent1 = find(nodes, id1);
            int parent2 = find(nodes, id2);
            nodes[parent2].parent = parent1;
            nodes[parent2].value = nodes[id1].value * values[i] / nodes[id2].value;
        }
        
        // Query
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Integer id1 = idMap.get(queries[i][0]);
            Integer id2 = idMap.get(queries[i][1]);
            
            if (id1 == null || id2 == null) 
                res[i] = -1.0;
            else {
                int parent1 = find(nodes, id1);
                int parent2 = find(nodes, id2);
                if (parent1 != parent2) 
                    res[i] = -1.0;
                else
                    res[i] = nodes[id2].value / nodes[id1].value;
            }
        }
        return res;
    }
    
    // Find
    public int find(Node[] nodes, int id) {
        int p = id;
        while (nodes[p].parent != p) {
            p = nodes[p].parent;
            nodes[id].value *= nodes[p].value;
        }
        nodes[id].parent = p;
        return p;
    }

    private class Node {
        int parent;
        double value;
        
        public Node(int index) {
            this.parent = index;
            this.value = 1.0;
        }
    }
}

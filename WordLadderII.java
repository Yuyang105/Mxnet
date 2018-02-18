/**
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
UPDATE (2017/1/20):
The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
*/

/**

无向图 ==> BFS ==> HashMap形式的树 ==> DFS
                    叶子节点为key，爸爸节点为value ==> 倒序DFS

时间复杂度 O(V + E) * wordList(max(length))     不确定.. 难讲
空间复杂度 O(n)

*/
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (beginWord == null || endWord == null || wordList == null
            || beginWord.length() == 0 || endWord.length() == 0 
            || wordList.size() == 0 || !wordList.contains(endWord)) return res;
        
        int current = 1, next = 0;
        boolean found = false;
        
        HashSet<String> visited = new HashSet<String>();
        HashSet<String> unvisited = new HashSet<String>(wordList);    // difference
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        Queue<String> queue = new LinkedList<String>();
        
        queue.offer(beginWord);
        
        while (!queue.isEmpty()) {
            String word = queue.poll();
            current--;
            
            // 对于每个poll出来的word，尝试每一种可能
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char origin = chars[i];
                for (char change = 'a'; change <= 'z'; change++) {
                    chars[i] = change;
                    String newWord = new String(chars);
                    if (unvisited.contains(newWord)) {
                        if (newWord.equals(endWord)) {
                            found = true;
                        }
                        if (visited.add(newWord)) {
                            queue.offer(newWord);
                            next++;
                        }
                        if (map.containsKey(newWord)) {
                            map.get(newWord).add(word);
                        }
                        else {
                            List<String> list = new ArrayList<String>();
                            list.add(word);
                            map.put(newWord, list);
                        }
                    }
                }
                chars[i] = origin;
            }
            
            // 尝试完当前word后，看这一层是不是收工了
            if (current == 0) {
                if (found) break;    // 看看可不可以提前收工
                current = next;
                next = 0;
                unvisited.removeAll(visited);  // 不同层不能访问同一个单词；但同一层能访问同一个单词
                visited.clear();
            }
        }
        
        dfs(res, new ArrayList<String>(), map, endWord, beginWord);
        return res;
    }
    
    private void dfs(List<List<String>> res, List<String> list, HashMap<String, List<String>> map, String word, String begin) {
        if (word.equals(begin)) {
            list.add(0, begin);
            res.add(new ArrayList<String>(list));
            list.remove(0);
            return;
        }
        list.add(0, word);
        if (map.get(word) != null) {
            for (String w: map.get(word))
                dfs(res, list, map, w, begin);
        }
        list.remove(0);
    }
}

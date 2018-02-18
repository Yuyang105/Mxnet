/**
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
UPDATE (2017/1/20):
The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.


*/

/**

用BFS做，初始版是用Queue，one-end solution。进阶可以考虑使用两个Set，两头同时找，two-end solution。

*/

class Solution {
    // 使用integers来记录层数，这是最初步的做法。
    // Time: O (n * word_length); Space: O(n).
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null
           || beginWord.length() == 0 || endWord.length() == 0 || wordList.size() == 0) return 0;
        
        HashSet<String> words = new HashSet<String>(wordList);
        if (words.contains(beginWord)) words.remove(beginWord);
        
        int moves = 1, current = 1, next = 0;
        
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        
        while (!queue.isEmpty()) {
            String target = queue.poll();
            current--;
        
            for (int i = 0; i < target.length(); i++) {
                char[] transform = target.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    transform[i] = j;
                    String temp = new String(transform);
                    if (words.contains(temp)) {
                        if (temp.equals(endWord))
                            return moves + 1;
                        queue.offer(temp);
                        words.remove(temp);
                        next++;
                    }
                }
            }
            if (current == 0) {
                current = next;
                next = 0;
                moves++;
            }
        }
        
        return 0;   
    }
    
    // 使用HashMap来记录层数，更加smart一点。
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null
           || beginWord.length() == 0 || endWord.length() == 0 || wordList.size() == 0
           || !wordList.contains(endWord)) return 0;
        
        HashSet<String> set = new HashSet<String>(wordList);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Queue<String> queue = new LinkedList<String>();
        
        if (set.contains(beginWord)) set.remove(beginWord);
        
        queue.offer(beginWord);
        map.put(beginWord, 1);
        
        while (!queue.isEmpty()) {
            String target = queue.poll();
            for (int i = 0; i < target.length(); i++) {
                char[] trans = target.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    trans[i] = j;
                    String temp = new String(trans);
                    if (temp.equals(endWord))
                        return map.get(target) + 1;
                    if (set.contains(temp)) {
                        queue.offer(temp);
                        set.remove(temp);
                        map.put(temp, map.get(target) + 1);
                    }
                }
            }
        }
        return 0;
    } 
    
    // 最fancy的要来了。。。
    // 我们用两个set，首尾同时找，有点binary search的意思。。因为同时找，所以我们还需要一个visited set
    // 我们可以不用Queue了
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null
           || beginWord.length() == 0 || endWord.length() == 0 || wordList.size() == 0
           || !wordList.contains(endWord)) return 0;
        
        HashSet<String> begin = new HashSet<String>();
        HashSet<String> end = new HashSet<String>();
        HashSet<String> visited = new HashSet<String>();
        
        begin.add(beginWord);
        end.add(endWord);
        //visited.add(beginWord);

        int moves = 1;      // res
        int len = beginWord.length();
        
        while (!begin.isEmpty() && !end.isEmpty()) {
            // swap ==> closer size for two sets
            if (begin.size() > end.size()) {
                HashSet<String> temp = begin;
                begin = end;
                end = temp;
            }
            
            HashSet<String> tempSet = new HashSet<String>();
            for (String target : begin) {
                char[] trans = target.toCharArray();
                for (int i = 0; i < len; i++) {
                    for (char j = 'a'; j <= 'z'; j++) {
                        char original = trans[i];
                        trans[i] = j;
                        String temp = new String(trans);
                        if (end.contains(temp))
                            return moves + 1;
                        if (!visited.contains(temp) && wordList.contains(temp)) {
                            tempSet.add(temp);
                            visited.add(temp);
                        }
                        trans[i] = original;
                    }
                }
            }
            begin = tempSet;
            moves++;  
        }
        return 0;
    }
}

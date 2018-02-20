/**

第一种做法，直接基于前一种HashMap的做法，加上一个DFS method，把问题变换成一个 Graph + DFS 的问题。
时间复杂度：O(|pairs| * |words|) (for the worst case)，空间复杂度：O(|pairs|)

第二种做法，用Union Find, 并查集的好处是并查集支持O(1)find和O(1)union。
时间复杂度：O(|words| + |pairs|), 空间复杂度：O(|pairs|)

*/

/**
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].
*/
class Solution {
    /** Union Find */
    public boolean areSentencesSimilarTwo1(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || words1.length != words2.length) return false;
        
        UnionFind uf = new UnionFind(words1, words2, pairs);
        
        for (String[] p : pairs)
            uf.union(p[0], p[1]);
        
        for (int i = 0; i < words1.length; i++) {
            if (!uf.find(words1[i]).equals(uf.find(words2[i])))
                return false;
        }
        return true;
    }
    
    class UnionFind {
        HashMap <String, String> father = new HashMap<String, String>();
        
        UnionFind(String[] words1, String[] words2, String[][] pairs) {
            for (int i = 0; i < words1.length; i++) {
                father.put(words1[i], words1[i]);
                father.put(words2[i], words2[i]);
            }
            for (String[] p : pairs) {
                father.put(p[0], p[0]);
                father.put(p[1], p[1]);
            }
            
        }
        
        String find(String s) {
            String parent = father.get(s);
            while (!parent.equals(father.get(parent)))
                parent = father.get(parent);
            String temp = "";
            String fa = s;
            while (!fa.equals(father.get(fa))) {
                temp = father.get(fa);
                father.put(fa, parent);
                fa = temp;
            }
            return parent;
        }
        
        void union(String x, String y) {
            String fa_x = find(x);
            String fa_y = find(y);
            
            if (!fa_x.equals(fa_y))
                father.put(fa_x, fa_y);
        }
    }
    
    /** Graph + DFS */
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        
        Map<String, Set<String>> similar_words = new HashMap<>();
        
        for (String[] pair : pairs) {
            if (!similar_words.containsKey(pair[0]))
                similar_words.put(pair[0], new HashSet<>());
            if (!similar_words.containsKey(pair[1]))
                similar_words.put(pair[1], new HashSet<>());
            similar_words.get(pair[0]).add(pair[1]);
            similar_words.get(pair[1]).add(pair[0]);
        }
        
        for (int i = 0; i < words1.length; ++i) {
            if (words1[i].equals(words2[i])) continue;
            if (!similar_words.containsKey(words1[i])) return false;
            if (!dfs(words1[i], words2[i], similar_words, new HashSet<String>())) return false;
        }
        
        return true;
    }
    
    public boolean dfs(String source, String target, Map<String, Set<String>> similar_words, Set<String> visited) {
        if (similar_words.get(source).contains(target)) return true;
        
        visited.add(source);
        for (String next : similar_words.get(source)) {
            if (!visited.contains(next) && dfs(next, target, similar_words, visited)) {
                return true;
            }
        }
        return false;
    }
}

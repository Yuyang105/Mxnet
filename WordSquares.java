/**
Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Example 2:

Input:
["abat","baba","atan","atal"]

Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
*/
class Solution {
    class TrieNode {
        private TrieNode[] children;
        private List<String> startWith;
        public TrieNode() {
            children = new TrieNode[26];
            startWith = new ArrayList<String>();
        }
    }
    
    class Trie {
        private TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        public void add(String[] words) {
            if (words == null) return;
            
            for (String word : words) {
                TrieNode cur = root;
                for (char c : word.toCharArray()) {
                    if (cur.children[c - 'a'] == null) {
                        cur.children[c - 'a'] = new TrieNode();
                    }
                    cur.children[c - 'a'].startWith.add(word);
                    cur = cur.children[c - 'a'];
                }
            }
        }
        
        public List<String> searchPrefix(String prefix) {
            List<String> res = new ArrayList<String>();
            TrieNode cur = root;
            for (char c : prefix.toCharArray()) {
                if (cur.children[c - 'a'] != null) {
                    cur = cur.children[c - 'a'];
                }
                else {
                    return res;
                }
            }
            res.addAll(cur.startWith);
            return res;
        }
    }
    
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<List<String>>();
        
        if (words == null || words.length == 0)
            return res;
        
        Trie trie = new Trie();
        trie.add(words);
        
        List<String> resBuilder = new ArrayList<String>();
        for (String word : words) {
            resBuilder.add(word);
            search(word.length(), resBuilder, trie, res);
            resBuilder.remove(resBuilder.size() - 1);
        }
        return res;
    }
    
    public void search(int len, List<String> resBuilder, Trie trie, List<List<String>> res) {
        if (resBuilder.size() == len) {
            res.add(new ArrayList<String>(resBuilder)); // copy to a new arrayList
            return;
        }
        
        int index = resBuilder.size();
        StringBuilder prefixBuilder = new StringBuilder();
        for (String s : resBuilder)
            prefixBuilder.append(s.charAt(index));
        List<String> startWith = trie.searchPrefix(prefixBuilder.toString());
        for (String s : startWith) {
            resBuilder.add(s);
            search(len, resBuilder, trie, res);
            resBuilder.remove(resBuilder.size() - 1);
        }
    }
}

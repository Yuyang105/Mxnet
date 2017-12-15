/**
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<String>();
        if (words == null || words.length == 0 || board == null) 
            return res;
        
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i]);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                search(board, i, j, trie.root, res);
            }
        }
        
        return res;
    }
    
    public void search(char[][] board, int x, int y, TrieNode node, List<String> res) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        if (node.isWord) {
            if (!res.contains(node.s)) {
                res.add(node.s);
            }
        }
        
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == 0 || node == null)
            return;
        
        if (node.children.containsKey(board[x][y])) {
            for (int i = 0; i < 4; i++) {
                char now = board[x][y];
                board[x][y] = 0;
                search(board, x + dx[i], y + dy[i], node.children.get(now), res);
                board[x][y] = now;
            }
        }
    }
    
    class Trie {
        TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode cur = root;
            HashMap<Character, TrieNode> curChildren = cur.children;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (curChildren.containsKey(chars[i])) {
                    cur = curChildren.get(chars[i]);
                }
                else {
                    TrieNode node = new TrieNode();
                    curChildren.put(chars[i], node);
                    cur = node;
                }
                curChildren = cur.children;
            }
            cur.isWord = true;
            cur.s = word;
        }
        
        public boolean search(String word) {
            TrieNode res = searchPosition(word);
            if (res == null)
                return false;
            else
                return res.isWord;
        }
        
        public TrieNode searchPosition(String s) {
            TrieNode cur = root;
            HashMap<Character, TrieNode> curChildren = cur.children;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if(curChildren.containsKey(chars[i])) {
                    cur = curChildren.get(chars[i]);
                    curChildren = cur.children;
                }
                else {
                    return null;
                }
            }
            return cur;
        }
    }
    
    class TrieNode {
        String s;
        HashMap<Character, TrieNode> children;
        boolean isWord;
        public TrieNode() {
            s = "";
            children = new HashMap<Character, TrieNode>();
            isWord = false;
        }
    }
}

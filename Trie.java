/**
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/

class Trie {    
    TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        HashMap<Character, TrieNode> curChildren = cur.children;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (curChildren.containsKey(chars[i])) {
                cur = curChildren.get(chars[i]);
            }
            else {
                TrieNode node = new TrieNode(chars[i]);
                curChildren.put(chars[i], node);
                cur = node;
            }
            curChildren = cur.children;
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode res = SearchWordNodePos(word);
        if (res == null)
            return false;
        else
            return res.isWord;
        
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (SearchWordNodePos(prefix) == null)
            return false;
        else
            return true;
    }
    
    private TrieNode SearchWordNodePos(String s) {
        TrieNode cur = root;
        HashMap<Character, TrieNode> curChildren = cur.children;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (curChildren.containsKey(chars[i])) {
                cur = curChildren.get(chars[i]);
                curChildren = cur.children;
            }
            else {
                return null;
            }
        }
        return cur;
    }
    
    class TrieNode {
        char c;
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        boolean isWord;
        
        public TrieNode() {
            
        }
        
        public TrieNode(char c) {
            this.c = c;
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

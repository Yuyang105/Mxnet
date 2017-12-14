/**
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
*/

import java.util.Map.Entry;

/** Trie */
class WordDictionary {
    TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
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
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        return(locater(root, 0, chars));
    }
    
    private boolean locater(TrieNode cur, int i, char[] chars) {
        if (i == chars.length) {
            return cur.isWord;
        }
         HashMap<Character, TrieNode> curChildren = cur.children;
        if (chars[i] == '.') {
            Iterator<Entry<Character, TrieNode>> iter = curChildren.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<Character, TrieNode> entry = iter.next();
                if (locater(entry.getValue(), i + 1, chars))
                    return true;
            }
            return false;
        }
        else if (curChildren.containsKey(chars[i])) {
            cur = curChildren.get(chars[i]);
            return locater(cur, i + 1, chars);
        }
        else
            return false;
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
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

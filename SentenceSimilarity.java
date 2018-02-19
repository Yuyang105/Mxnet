/**

首先，如果两个array的长度不等，直接弹false。

brute froce：可以逐个取第i对词，对第一个词，遍历所有的pairs，直到有一个近义词是words2。时间复杂度：O(|words| * |pairs|)，空间复杂度：O(1)。

HashSet: 我们可以牺牲一下空间复杂度。起一个HashMap来记录每个词的近义词，key是我们的keyword，value为HashSet，set存储所有近义词。
         时间复杂度：O(|words| + |pairs|), 空间复杂度：O(|pairs|)

*/

/**
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.

However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].
*/
class Solution {
    /** 用HashMap记录，keyword以及keyword的近义词 */
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
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
            if (!similar_words.get(words1[i]).contains(words2[i])) return false;
        }
        
        return true;
    }
    
    /** 不用HashMap和HashSet，直接生找。时间复杂度好像有点高。*/
    public boolean areSentencesSimilar1(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || words1.length != words2.length) return false;
        
        for (int i = 0; i < words1.length; i++) {
            String word1 = words1[i];
            String word2 = words2[i];
            Boolean flag = false;
            if (word1.equals(word2)) {
                continue;
            }
            for (int j = 0; j < pairs.length; j++) {
                if ((pairs[j][0].equals(word1) && pairs[j][1].equals(word2))
                   || (pairs[j][0].equals(word2) && pairs[j][1].equals(word1))
                   || (pairs[j][1].equals(word1) && pairs[j][0].equals(word2))
                   || (pairs[j][1].equals(word2) && pairs[j][0].equals(word1))) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                return false;
        }
        return true;
    }
}

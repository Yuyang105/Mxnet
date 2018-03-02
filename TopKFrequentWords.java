/**

方法一：
直接HashMap计数，然后keyset存到res，并且调用排序算法。直接排，取前k。

方法二：
直接HashMap计数，然后定义pq的比较方法，然后把keySet都插进去。然后倒回list，反转，输出。

*/

/**
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.

*/
class Solution {
    public List<String> topKFrequent1(String[] words, int k) {
        HashMap<String, Integer> count = new HashMap<>();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> res = new ArrayList(count.keySet());
        Collections.sort(res, (w1, w2) -> count.get(w1) != count.get(w2) ? 
                        count.get(w2) - count.get(w1) : w1.compareTo(w2));
        
        return res.subList(0, k);
    }
    
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> count = new HashMap<>();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        
        PriorityQueue<String> heap = new PriorityQueue<String>(
            (w1, w2) -> count.get(w1) != count.get(w2) ? 
                        count.get(w1) - count.get(w2) : w2.compareTo(w1));
        
        for (String word : count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }
        
        List<String> res = new ArrayList<String>();
        while (!heap.isEmpty()) 
            res.add(heap.poll());
        Collections.reverse(res);
        return res;
    }
    
}

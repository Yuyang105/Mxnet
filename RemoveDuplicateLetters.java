/**

用HashMap来记录每个字符出现的最后一个index。
然后辅助方法，找map里面，最靠前的一个index，也就是说，map中有一个元素，必须在这个index前被安排出去。

然后从0开始，到min_index结束，找最小的char放进去，从map中移除。
start从这一位开始看。
如果这个char就是min_index对应的char，更新一次，找下一个必须要安排一个元素出去的index。

*/

/**
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
*/
class Solution {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return s;
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        char[] res = new char[map.size()];
       
        int start = 0;
        int end = findMinNextPos(map);
        
        for (int i = 0; i < res.length; i++) {
            char minChar = 'z' + 1;
            for (int j = start; j <= end; j++) {
                if (map.containsKey(s.charAt(j)) && s.charAt(j) < minChar) {
                    minChar = s.charAt(j);
                    start = j + 1;  // 排序不能错
                }
            }
            res[i] = minChar;
            map.remove(minChar);    // 这个最小的已经用掉了  
            
            if (s.charAt(end) == minChar) {
                end = findMinNextPos(map);
            }
        }
        
        return new String(res);
        
    }
    
    private int findMinNextPos(HashMap<Character,Integer> map) {
        int res = Integer.MAX_VALUE;
        for (int num : map.values()) {
            res = Math.min(res, num);
        }
        return res;
    }
}

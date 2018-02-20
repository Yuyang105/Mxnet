/**

验证回文串，允许删除一个字符。所以，额外添加一个helper method。
主方法中，一旦发现一次不匹配，弹给辅助方法。
辅助方法，一旦发现一次不匹配，直接拜拜。

*/

/**
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
*/

class Solution {
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        
        int low = 0, high = s.length() - 1;
        
        while (low < high) {
            if (s.charAt(low) != s.charAt(high))
                return valid(s, low + 1, high) || valid(s, low, high - 1);
            low++;
            high--;
        }
        return true;
    }
    
    public boolean valid(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low) != s.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }
}

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

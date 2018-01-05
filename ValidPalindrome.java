/**
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/

class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        
        s = s.toLowerCase();
        int low = 0, high = s.length() - 1;
        while (low < high) {
            if (!Character.isLetterOrDigit(s.charAt(low)))
                low++;
            else if (!Character.isLetterOrDigit(s.charAt(high)))
                high--;
            else if (s.charAt(low) != s.charAt(high))
                return false;
            else {
                low++;
                high--;
            }
        }
        return true;
    }
}

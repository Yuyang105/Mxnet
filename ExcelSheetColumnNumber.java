/**
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
Credits:
Special thanks to @ts for adding this problem and creating all test cases.
*/

class Solution {
    public int titleToNumber(String s) {
        int res = 0;
        if (s == null) return res;
        
        for (int i = s.length() - 1, j = 0; i > 0; i--, j++)
            res += Math.pow(26, i) * (s.charAt(j) - 'A' + 1);
        
        return res + s.charAt(s.length() - 1) - 'A' + 1;
    }
}

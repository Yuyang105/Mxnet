/** 
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".
*/

class Solution {
    /* a e i o u */
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return s;
        
        StringBuilder sb = new StringBuilder(s);
        HashSet<Character> set = new HashSet<Character>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        
        int low = 0, high = s.length() - 1;
        while (low < high) {
            while (low < high && !set.contains(s.charAt(low)))
                low++;
            while (low < high && !set.contains(s.charAt(high)))
                high--;
            
            if (set.contains(s.charAt(low)) && set.contains(s.charAt(high))) {
                sb.setCharAt(low, s.charAt(high));
                sb.setCharAt(high, s.charAt(low));
            }
            low++;
            high--;
        }
        return sb.toString();
    }
}

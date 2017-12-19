/**
Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

Examples:
Input:
letters = ["c", "f", "j"]
target = "a"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "c"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "d"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "g"
Output: "j"

Input:
letters = ["c", "f", "j"]
target = "j"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "k"
Output: "c"
Note:
letters has a length in range [2, 10000].
letters consists of lowercase letters, and contains at least 2 unique letters.
target is a lowercase letter.
*/
class Solution {
    /** Binary solution. O(log n) */
    public char nextGreatestLetter(char[] letters, char target) {
        int start = 0, end = letters.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (letters[mid] > target) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }
        return letters[start % letters.length];
    }
    
    /**
     * Linear solution: also works if the array is not sorted. O(n) *
    public char nextGreatestLetter(char[] letters, char target) {
        char res = '?';
        if (letters == null || letters.length == 0) return res;
        
        int min = 26, distance = 26;
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > target)
                distance = letters[i] - target;
            else if (letters[i] < target)
                distance = 'z' - target + letters[i] - 'a' + 1;
            if (distance < min) {
                min = distance;
                res = letters[i];
            }
            
        }
        return res;
    }
    */
}

/**

贪婪算法：
The second-last 0 must be the end of a character (or, the beginning of the array if it doesn't exist). 
Looking from that position forward, the array bits takes the form [1, 1, ..., 1, 0] 
where there are zero or more 1's present in total. 

It is easy to show that the answer is true if and only if there are an even number of ones present.

*/

/**
We have two special characters. The first character can be represented by one bit 0. The second character can be represented by two bits (10 or 11).

Now given a string represented by several bits. Return whether the last character must be a one-bit character or not. The given string will always end with a zero.

Example 1:
Input: 
bits = [1, 0, 0]
Output: True
Explanation: 
The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.
Example 2:
Input: 
bits = [1, 1, 1, 0]
Output: False
Explanation: 
The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.
Note:

1 <= len(bits) <= 1000.
bits[i] is always 0 or 1.
*/
class Solution {
    public boolean isOneBitCharacter1(int[] bits) {
        boolean res = false;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] == 0)
                res = true;
            else {
                res = false;
                i++;
            }
        }
        return res;
    }
    
    public boolean isOneBitCharacter(int[] bits) {
        int i = bits.length - 2;
        while (i >= 0 && bits[i] > 0) i--;
        return (bits.length - i) % 2 == 0;
    }
}

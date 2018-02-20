/**
两种做法：
1. 转成String
这个做法比较cheating。直接把数字转成一个binary digits String. 然后, 简单检查相邻两个digits都不相同。

2. 不断除二
We can get the last bit and the rest of the bits via n % 2 and n // 2 operations. 
Let's remember cur, the last bit of n. If the last bit ever equals the last bit of the remaining, 
then two adjacent bits have the same value, and the answer is False. Otherwise, the answer is True.
Also note that instead of n % 2 and n // 2, we could have used operators n & 1 and n >>= 1 instead.

*/

/**
Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.

Example 1:
Input: 5
Output: True
Explanation:
The binary representation of 5 is: 101
Example 2:
Input: 7
Output: False
Explanation:
The binary representation of 7 is: 111.
Example 3:
Input: 11
Output: False
Explanation:
The binary representation of 11 is: 1011.
Example 4:
Input: 10
Output: True
Explanation:
The binary representation of 10 is: 1010.
*/
class Solution {
    // Convert to String
    public boolean hasAlternatingBits(int n) {
        String cheat = Integer.toBinaryString(n);
        if (cheat.length() < 2) return true;  
        for (int i = 0; i < cheat.length() - 1; i++) {
            if (cheat.charAt(i) == cheat.charAt(i+1))
                return false;
        }
        return true;
    }
    
    // Divide By Two
    public boolean hasAlternatingBits2(int n) {
        int cur = n % 2;
        n /= 2;
        while (n > 0) {
            if (cur == n % 2) return false;
            cur = n % 2;
            n /= 2;
        }
        return true;
    }
}

/**
Given an integer, write a function to determine if it is a power of two.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
*/

class Solution {
    // iterative
    public boolean isPowerOfTwoI(int n) {
        if (n == 0) return false;
        while (n % 2 == 0) n = n / 2;
        return n == 1;
    }
    
    // recursive
    public boolean isPowerOfTwoII(int n) {
        return n > 0 && (n == 1 || (n % 2 == 0 && isPowerOfTwo(n / 2)));
    }
    
    // bitwise
    // x & (x - 1) 消除二进制中最右侧的1
    public boolean isPowerOfTwoIII(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
    
    // bitwise same trick
    public boolean isPowerOfTwo(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }
    
}

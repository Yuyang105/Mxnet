/**
Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
*/

class Solution {
    // iterative
    public boolean isPowerOfThreeI(int n) {
        if (n == 0) return false;
        while (n % 3 == 0) n = n / 3;
        return n == 1;
    }
    
    // 耍流氓，直接拿最高数找余
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}

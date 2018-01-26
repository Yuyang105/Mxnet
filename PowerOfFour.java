/** 
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?

Credits:
Special thanks to @yukuairoy for adding this problem and creating all test cases.
*/

class Solution {
    // iterative
    public boolean isPowerOfFourI(int num) {
        if (num == 0) return false;
        while (num % 4 == 0) num = num / 4;
        return num == 1;
    }
    
    // cheat
    // 确保所有的1都在奇数位上
    public boolean isPowerOfFour(int num) {
        return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0x55555555) == num);
    }
}

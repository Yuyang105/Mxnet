/**

其实可以转换成：
x * m + y * n = z, 比如 3 * -2 + 5 * 2 = 4.
贝祖定理：z = gcd(x, y) * i

辗转相除 求 最大公约数

Time : < O(n)
Space: O(n).

*/

/**
You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available. You need to determine whether it is possible to measure exactly z litres using these two jugs.

If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.

Operations allowed:

Fill any of the jugs completely with water.
Empty any of the jugs.
Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
Example 1: (From the famous "Die Hard" example)

Input: x = 3, y = 5, z = 4
Output: True
Example 2:

Input: x = 2, y = 6, z = 5
Output: False
Credits:
Special thanks to @vinod23 for adding this problem and creating all test cases.
*/
class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) return false;
        if (x == z || y == z || x + y == z) return true;
        return z % gcd(x, y) == 0;
    }
    
    // 辗转相除
    private int gcd(int a , int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}

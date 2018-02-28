/**
首先考虑边界条件：
1. 正负号
2. 越界
3. = 0 的情况

时间复杂度：O(logn)，空间复杂度：O(logn)。

对半分，sum += sum， multiple += multiple。

*/

/**
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
*/
class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        if (dividend == 0) return 0;
        
        int sign = 1;   //正负号
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) sign = -1;
        
        // 越界
        long longDividend = Math.abs((long)dividend);
        long longDivisor = Math.abs((long)divisor);
        
        if (longDividend < longDivisor) return 0;
        
        long longRes = divide(longDividend, longDivisor);
        if (longRes > Integer.MAX_VALUE)
            longRes = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        return (int)(sign * longRes);
    }
    
    private long divide(long divident, long divisor) {
        if (divident < divisor) return 0;
        long sum = divisor, res = 1;
        while (sum + sum <= divident) {
            sum += sum;
            res += res;
        }
        return res + divide(divident - sum, divisor);
    }
}

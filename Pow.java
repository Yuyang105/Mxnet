/**

1) 最直观容易想到的方法就是用递归方法求n个x的乘积，注意考虑n的正负号，时间复杂度为O(n)

2) 考虑到n个x相乘式子的对称关系，可以对上述方法进行改进，从而得到一种时间复杂度为O(logn)的方法，递归关系可以表示为
   pow(x,n) = pow(x, n/2) * pow(x, n - n/2)
   

递归、遍历。


*/

/**
Implement pow(x, n).


Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
*/
class Solution {
    public double myPow1(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }
    
    
    public double myPow(double x, int m) {
        double temp = x;
        if (m == 0) return 1;
        temp = myPow(x, m / 2);
            
        if (m % 2 == 0) return temp * temp;
        else {
            if(m > 0)
                return x * temp * temp;
            else
                return (temp *temp) / x;
        }
    }
}

/**
Description:

Count the number of prime numbers less than a non-negative number, n.

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.
*/
class Solution {
    public int countPrimes(int n) {
        // 厄拉多塞筛法
        boolean[] notPrime = new boolean[n];
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                res++;
                for (int j = 1; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }   
        }
        return res;
    }
}

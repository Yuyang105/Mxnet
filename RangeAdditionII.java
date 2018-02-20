/**

这道题目给了我们一个 m*n 的matrix， 起初都是0， 根据operation给其中一部分区域加1。最后要return 最大值integer的个数。

我们可以从另一个角度出发，把这个题目转化成图形来理解，最大的值的区域就是所有operation的交集。
如何找到这个区域呢，我们需要记录一个min x 和min y 来求出交集的区域 = x*y， 相当于在求面积。

*/

/**
Given an m * n matrix M initialized with all 0's and several update operations.

Operations are represented by a 2D array, and each operation is represented by an array with two positive integers a and b, which means M[i][j] should be added by one for all 0 <= i < a and 0 <= j < b.

You need to count and return the number of maximum integers in the matrix after performing all the operations.

Example 1:
Input: 
m = 3, n = 3
operations = [[2,2],[3,3]]
Output: 4
Explanation: 
Initially, M = 
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]

After performing [2,2], M = 
[[1, 1, 0],
 [1, 1, 0],
 [0, 0, 0]]

After performing [3,3], M = 
[[2, 2, 1],
 [2, 2, 1],
 [1, 1, 1]]

So the maximum integer in M is 2, and there are four of it in M. So return 4.
Note:
The range of m and n is [1,40000].
The range of a is [1,m], and the range of b is [1,n].
The range of operations size won't exceed 10,000.
*/

class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        int a = m, b = n;
        for (int[] op : ops) {
            a = Math.min(a, op[0]);
            b = Math.min(b, op[1]);
        }
        return a * b;
    }
}

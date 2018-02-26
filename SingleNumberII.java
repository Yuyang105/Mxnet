/**

位运算做法：
    对于同一个数：
                1. 存入ones里
                2. 清空ones，存入twos里
                3. twos进行清空

00 -> 10 -> 01 -> 00

e.g.    1 1 1 2

1: 0001
ones: 0001 & 1111 -> 0001
twos: 0001 & 1110 -> 0000

1: 0001
ones: 0000 & 1111 -> 0000
twos: 0001 & 0000 -> 0001

1: 0001
ones: 0001 & 1110 -> 0000
twos: 0000 & 1111 -> 0000

*/
class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int n : nums) {
            ones = (ones ^ n) & ~twos;
            twos = (twos ^ n) & ~ones;
        }
        return ones;
    }
}

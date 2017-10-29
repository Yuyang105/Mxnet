class Solution {
    public int hammingDistance(int x, int y) {
        /** cheating by using built-in function, haha
        return Integer.bitCount(x ^ y);
        */

        int res = 0;
        int num = x ^ y;
        while (num != 0) {
            num = num & (num - 1);  // where magic happens
            res++;
        }
        return res;
    }
}

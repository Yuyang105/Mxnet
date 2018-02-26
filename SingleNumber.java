/**

位运算： 异或  ->    相同为0，不同为1.
                      1 1 : 0
                      0 0 : 0           ->  N ^ N = 0
                      1 0 : 1           
                      0 1 : 1           ->  0 ^ N = N
        所以：
        if N is the single number
        N1 ^ N1 ^ N2 ^ N2 ^…^ Nx ^ Nx ^ N
        = (N1^N1) ^ (N2^N2) ^…^ (Nx^Nx) ^ N
        = 0 ^ 0 ^ …^ 0 ^ N
        = N                  
        Time: O(n), Space: O(1).

*/

class Solution {
    public int singleNumber(int[] nums) {
        int magic = 0;
        for (int i : nums) magic = magic ^ i;
        return magic;
    }
}

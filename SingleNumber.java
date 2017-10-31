class Solution {
    public int singleNumber(int[] nums) {
        int magic = 0;
        for (int i : nums) magic = magic ^ i;
        return magic;
    }
}

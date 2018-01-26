/**
Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.
*/

class Solution {
    // native solution
    public int[] plusOneI(int[] digits) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(0);
        for (int d : digits)
            list.add(d);
        
        for (int i = digits.length; i >= 0; i--) {
            if (list.get(i) != 9) {
                list.set(i, list.get(i) + 1);
                break;
            }
            list.set(i, 0);
        }
        
        if (list.get(0) == 0)
            list.remove(0);
        
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i).intValue();
        return res;
    }
    
    // revised one
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }
}

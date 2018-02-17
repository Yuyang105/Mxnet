/**
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*/

public class Solution {
    public String intToRoman(int num) {
        // String res = "";
        // String symbol[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
        //                    "IX", "V", "IV", "I"};
        // int value[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        // for (int i = 0; num != 0; i++) {
        //     while (num >= value[i]) {
        //         num -= value[i];
        //         res += symbol[i];
        //     }
        // }
        // return res;
        
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[num % 1000 / 100] + X[num % 100 / 10] + I[num % 10];
    }
}

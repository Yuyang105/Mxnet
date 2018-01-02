/**
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/

class Solution {
    /**
     *  Naive solution : based on Strobogrammatic Number II
     */
    public int strobogrammaticInRange(String low, String high) {
        int res = 0;
        long l = Long.parseLong(low);
        long h = Long.parseLong(high);
        for (int i = low.length(); i <= high.length(); i++) {
            List<String> list = helper(i, i);
            for (String s : list)
                if (Long.parseLong(s) >= l && Long.parseLong(s) <= h)
                    res++;
        }
        return res;
    }
    
    public List<String> helper(int n, int m) {
        if (n == 0) return new ArrayList<String>(Arrays.asList(""));
        if (n == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));
        List<String> list = helper(n - 2, m);
        List<String> res = new ArrayList<String>();
        for (String s : list) {
            if (n != m)
                res.add("0" + s + "0");
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }
        return res;
    }
}

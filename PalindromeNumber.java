public class Solution {
    public boolean isPalindrome(int x) {
        // negative number
        if (x < 0)
            return false;
        
        int len = 1;    // get length
        while (x / len >= 10)
            len *= 10;
        
        while (x > 0) {
            // get head and tail
            int head = x / len;
            int tail = x % 10;
            
            if (head != tail)
                return false;
            else {
                // remove the head and tail
                x = (x % len) / 10;
                len /= 100;
            }
        }
        
        return true;
        
        /*
        long result = 0;
        int temp = Math.abs(x);
        while (temp > 0) {
            result *= 10;
            result += temp % 10;
            if (result > Integer.MAX_VALUE)
                return false;
            temp /= 10;
        }
        return (x == result ? true : false);
        */
    }
}

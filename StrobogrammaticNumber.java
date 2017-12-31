/**
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
*/
class Solution {
    /** Original solution */
    public boolean isStrobogrammatic(String num) {
        if (num == null) return false;
        
        // 0 1 8
        if (num.contains("2") || num.contains("3") || num.contains("4")
           || num.contains("5") || num.contains("7"))
            return false;
        
        // 6 9
        int six = 0, nine = 0;
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '6')
                six++;
            else if (num.charAt(i) == '9')
                nine++;
        }
        if (six != nine)
            return false;

        
        // check palindrome and 69
        int l = 0;
        int r = num.length() - 1;
        while (l < r) {
            if (num.charAt(l) != num.charAt(r)) {
                if (num.charAt(l) != '6' && num.charAt(l) != '9')
                    return false;
                if (num.charAt(l) == '6' && num.charAt(r) != '9')
                    return false;
                if (num.charAt(l) == '9' && num.charAt(r) != '6')
                    return false;
            }
            l++;
            r--;
        }
        return true;
    }
    
    /** 
     * Using hashmap *
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');

        int l = 0, r = num.length() - 1;
        while (l <= r) {
            if (!map.containsKey(num.charAt(l))) return false;
            if (map.get(num.charAt(l)) != num.charAt(r))
                return false;
            l++;
            r--;
        }

        return true;
    }
    */
    
    /** 
     * Amazing one *
     public boolean isStrobogrammatic(String num) {
        for (int i=0, j=num.length()-1; i <= j; i++, j--)
            if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j)))
                return false;
        return true;
    }
    */
}

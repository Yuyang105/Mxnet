/**
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.
*/

class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        if (word == null || abbr == null) return true;
        
        int index =  0, len = word.length();
        String[] parts = abbr.split("[^a-z0-9]+|(?<=[a-z])(?=[0-9])|(?<=[0-9])(?=[a-z])");
        if (parts.length == 0) return true;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].length() > 0 && Character.isDigit(parts[i].charAt(0))) {
                if (parts[i].charAt(0) == '0')
                    return false;
                index += Integer.parseInt(parts[i]);
                if (index > len)  {
                    System.out.println("1");
                    return false;
                }
            }
            else {
                for (int j = 0; j < parts[i].length(); j++) {
                    if (index > len) {
                        System.out.println("2");
                        return false;
                    }
                    if (index < len && parts[i].charAt(j) != word.charAt(index)) {
                        System.out.println("index: " + index);
                        System.out.println("charAt j : " + parts[i].charAt(j));
                        System.out.println("charAt index : " + word.charAt(index));
                        System.out.println("3");
                        return false;
                    }
                    index++;
                }
            }
        }
        if (index != len) {
            System.out.println("4");
            return false;
        }
        return true;
    }
    
    /** 
     * clear version
    public boolean validWordAbbreviation(String word, String abbr) {
        if(word == null || abbr == null) return false;
        int num = 0;
        int idx = 0;
        
        for(char c: abbr.toCharArray()){
            if(c == '0' && num == 0) return false;
            if(c >= '0' && c <= '9'){
                num = num*10 + (c-'0');
            }else{
                idx += num;
                if(idx >= word.length() || c != word.charAt(idx)) return false;
                num = 0;
                idx++;
            }
        }
        
        return idx+num == word.length() ? true : false;
    }
    */
}

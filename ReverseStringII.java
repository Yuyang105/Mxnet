class Solution {
    public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0) return s;
        char[] str = s.toCharArray();
        int i = 0;
        while (i < s.length()) {
            int j = Math.min(i + k - 1, s.length() - 1);
            swap(str, i , j);
            i += 2 * k;
        }
        return String.valueOf(str);
    }
    
    private void swap(char[] str, int i , int j) {
        while (i < j) {
            char temp = str[i];
            str[i++] = str[j];
            str[j--] = temp;
        }
    }
}

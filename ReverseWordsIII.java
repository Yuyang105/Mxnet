class Solution {
    public String reverseWords(String s) {
        StringBuilder res = new StringBuilder();
        String[] words = s.split(" ");
        for (int i = 0; i < words.length; i++) {
            res.append(new StringBuffer(words[i]).reverse().toString());
            res.append(" ");
        }
        res.setLength(res.length() - 1);
        return res.toString();
    }
}

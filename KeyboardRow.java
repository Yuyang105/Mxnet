// 3 ms, without fancy tricks
class Solution {
    public String[] findWords(String[] words) {
        String first = "qwertyuiopQWERTYUIOP";
        String second = "asdfghjklASDFGHJKL";
        String third = "zxcvbnmZXCVBNM";
        
        List<String> res = new ArrayList<String>();
        
        for (int i = 0; i < words.length; i++) {
            int a = 0, b = 0, c = 0;
            for (int j = 0; j < words[i].length(); j++) {
                if (first.indexOf(words[i].charAt(j)) >= 0)
                    a = 1;
                else if (second.indexOf(words[i].charAt(j)) >= 0)
                    b = 1;
                else if (third.indexOf(words[i].charAt(j)) >= 0)
                    c = 1;
            }
            if (a + b + c == 1)
                res.add(words[i]);
        }
        return res.toArray(new String[res.size()]);
    }
}

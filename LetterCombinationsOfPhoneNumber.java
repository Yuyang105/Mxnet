/**

两种做法，一种是BFS，一种是DFS。

BFS：通过queue, 做成一个iterative的解法。

DFS：传统的backtracking，通过递归调用helper()完成遍历。
    
*/

/**
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
*/
class Solution {
    // bfs
    public List<String> letterCombinations(String digits) {
        LinkedList<String> res = new LinkedList<String>();
        if (digits == null || digits.length() == 0) return res;
        
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        res.add("");
        for (int i = 0; i < digits.length(); i++) {
            int num = digits.charAt(i) - '0';
            while (res.peek().length() == i) {
                String prev = res.remove();
                for (int j = 0; j < mapping[num].length(); j++) {
                    res.add(prev + mapping[num].charAt(j));
                }
            }
        }
        return res;
    }
    
    
    // dfs
    public List<String> letterCombinations1(String digits) {
        ArrayList<String> res = new ArrayList<String>();
        if (digits == null || digits.length() == 0) return res;
        
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        helper(res, mapping, digits, "", 0);
        return res;
    }
    
    private void helper(List<String> res, String[] mapping, String digits, String temp, int index) {
        if (temp.length() == digits.length()) {
            res.add(temp);
            return;
        }
        int num = digits.charAt(index) - '0';
        for (int i = 0; i < mapping[num].length(); i++) {
            helper(res, mapping, digits, temp + mapping[num].charAt(i), index + 1);
        }
    }
}

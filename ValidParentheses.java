/**
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

/**

用stack来做，只push左括号进去。
一旦手上是右括号，就检查stack是否为空，不为空的并pop出来正是这个右括号，通过。
最后检查一下，stack里面还有没有残余的左括号。

时间：O(n)；空间：O(n)。

==================================
Follow-up: 分布式，多线程，解决长输入。

Based on property of balanced parenthesis that all prefix 
must have more or equal opened brackets than closed ones, 
and totally number of open brackets equal to closed ones.

Let P1, P2, P3, ... Pn be n systems, ith system having String Si.
We have to know if the concatenated S1 + S2 + ... + Sn is a balanced paranthesis.

对于每个字符串S，初始化value=0，从左到右，
每遇到一个open bracket，value++；每遇到一个close bracket， value--；

所以每个子系统，只用在它手上的substring里找两个东西：
* Least value : the least value that we encountered     也就是每个sub中出现过的最小值
* Total value : the final value we get for the string   也就是最终总值

最后，要满足括号成立，我们看三个条件：
1. leastValue(1) = 0                   ==> 打一开始，没有起手一个close bracket.     ↓ 其实也是这件事 ↓
2. For ith String, {sum[j = 1, i - 1](totalValue)} + leastValue(i) >= 0     ==> 前i-1个的总值加上我的最低值，没有小于0.
3. 最终，所有总值相加等于0.

*/
public class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        
        Stack<Character> stack = new Stack<Character>();
        char[] c = s.toCharArray();
        
        for (int i = 0; i < c.length; i++) {
        	if (c[i] == '(') {
                stack.push(')');
            }
            else if (c[i] == '{') {
                stack.push('}');
            }
            else if (c[i] == '[') {
                stack.push(']');
            }
            else if (stack.isEmpty() || stack.pop() != c[i]) {
                return false;
            }
        }
        
        return stack.isEmpty();        
    }
}

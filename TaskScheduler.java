/**

和358, 767重排字符串的做法一样
需要一个helper method来抓下一个最大的，优先放最大的。

*/

/**
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
Note:
The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
*/

class Solution {
	public int leastInterval(char[] tasks, int n) {
	    if (tasks == null || tasks.length == 0) return 0;

        int[] count = new int[26];
        int[] valid = new int[26];
        
        int res = tasks.length;

        // count frequency
        for (int i = 0; i < tasks.length; i++) {
            count[tasks[i] - 'A']++;
        }

        // organize order
        for (int i = 0; i < res; i++) {
            int next = findNext(count, valid, i);
            if (next < 0) {
                res++;
            }
            else {
                valid[next] += n + 1;
                count[next]--;
            }
        }
        return res;
    }

    private int findNext(int[] count, int[] valid, int index) {
        int max = 0, res = -1;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > max && valid[i] <= index) {
                max = count[i];
                res = i;
            }
        }
        return res;
    }
}
